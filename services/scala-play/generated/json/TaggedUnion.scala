package json

object taggedunion {
  import io.circe._
  import shapeless.{:+:, HNil, CNil, Coproduct, Generic, Inl, Inr, Lazy}

  implicit val cnilEncoder: Encoder[CNil] =
    Encoder.instance(cnil => throw new Exception("Inconceivable!"))

  implicit val cnilDecoder: Decoder[CNil] =
    Decoder.instance(a => Left(DecodingFailure("Discriminator field or wrapped object not recognized", a.history)))

  implicit def coproductEncoder[H, T <: Coproduct](
    implicit hEncoder: Lazy[Encoder[H]],
    tag: Lazy[Tag[H]],
    config: Lazy[Config[H]],
    tEncoder: Encoder[T]
  ): Encoder[H :+: T] = Encoder.instance{
    case Inl(head) =>
      encodeWithConfig(config.value, tag.value, hEncoder.value)(head)
    case Inr(tail) => tEncoder(tail)
  }

  implicit def coproductDecoder[H, T <: Coproduct](
    implicit hDecoder: Lazy[Decoder[H]],
    tag: Lazy[Tag[H]],
    config: Lazy[Config[H]],
    tDecoder: Decoder[T]
  ): Decoder[H :+: T] = Decoder.instance{ cursor =>
    decodeWithConfig(cursor, tag.value, config.value, hDecoder) match {
      case Left(NotMatch)   => tDecoder.tryDecode(cursor).map(Inr(_))
      case Right(value)     => Right(Inl[H, T](value))
      case Left(InternalParsingError(circeError))           => Left[DecodingFailure, H :+: T](circeError)
      case Left(DiscriminatorFieldCorrupted(circeError))    => Left[DecodingFailure, H :+: T](circeError)
    }
  }

  implicit val hnilEncoder: Encoder[HNil] =
    Encoder.instance(hnil => throw new Exception("Inconceivable!"))

  implicit val hnilDecoder: Decoder[HNil] =
    Decoder.instance(a => Left(DecodingFailure("Inconceivable!", a.history)))

  def deriveUnionEncoder[A, R](implicit gen: Generic.Aux[A, R], enc: Encoder[R]): Encoder[A] = {
    Encoder.instance(a => enc(gen.to(a)))
  }

  def deriveUnionDecoder[A, R](implicit gen: Generic.Aux[A, R], dec: Decoder[R]): Decoder[A] = {
    Decoder.instance(a => dec(a).map(gen.from))
  }

  def deriveUnionCodec[A, R](
    implicit
    gen: Generic.Aux[A, R],
    enc: Encoder[R],
    dec: Decoder[R]
  ): Codec[A] = new Codec[A] {
    override def apply(a: A): Json = enc(gen.to(a))
    override def apply(c: HCursor): Decoder.Result[A] = dec(c).map(gen.from)
  }

  trait Tag[A]{
    def name: String
  }

  object Tag{
    def apply[A](string: String): Tag[A] =
      new Tag[A] {
        override def name: String = string
      }
  }

  sealed trait CoproductConfig[-A]
  case class Discriminator[A](fieldName: String) extends CoproductConfig[A]
  case class WrappedObject[A]() extends CoproductConfig[A]

  trait Config[-A]{
    def coproduct: CoproductConfig[A]
  }

  object Config{
    def discriminator[A](fieldName: String) = new Config[A]{
      def coproduct: CoproductConfig[A] = Discriminator(fieldName)
    }

    def wrapped[A] = new Config[A]{
      def coproduct: CoproductConfig[A] = WrappedObject()
    }
  }

  def encodeWithConfig[H](config: Config[H], tag: Tag[H], encoder: Encoder[H]) = Encoder.instance[H]{ value =>
    config.coproduct match {
      case discriminator: Discriminator[H] =>
        encoder(value)
          .mapObject(js =>
            js.+:(discriminator.fieldName, Json.fromString(tag.name))
          )
      case _: WrappedObject[H] =>
        Json.obj((tag.name, encoder(value)))
    }
  }

  sealed trait DecodingError
  case object NotMatch extends DecodingError
  case class InternalParsingError(circeError: DecodingFailure) extends DecodingError
  case class DiscriminatorFieldCorrupted(circeError: DecodingFailure) extends DecodingError

  def decodeWithConfig[H](cursor: HCursor, tag: Tag[H], config: Config[H], hDecoder: Lazy[Decoder[H]]): Either[DecodingError, H] = {
    config.coproduct match {
      case Discriminator(fieldName) =>
        cursor.downField(fieldName).as[String] match {
          case Left(value)  =>
            Left(DiscriminatorFieldCorrupted(value))

          case Right(discriminator) =>
            if(discriminator == tag.name){
              hDecoder.value.tryDecode(cursor) match {
                case Left(value)  => Left(InternalParsingError(value))
                case Right(value) => Right(value)
              }
            } else {
              Left(NotMatch)
            }
        }

      case WrappedObject() => {
        hDecoder.value.tryDecode(cursor.downField(tag.name)) match {
          case Left(value)  =>
            Left(NotMatch)

          case Right(discriminator) =>
            Right(discriminator)
        }
      }
    }
  }
}