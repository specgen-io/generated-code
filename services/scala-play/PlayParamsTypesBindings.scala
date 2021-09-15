package spec.controllers

import play.api.mvc.{PathBindable, QueryStringBindable}

object PlayParamsTypesBindings {
  implicit def queryBindableParser[T](implicit stringBinder: QueryStringBindable[String], codec: Codec[T]): QueryStringBindable[T] = new QueryStringBindable[T] {
    override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, T]] =
      for {
        valueStr <- stringBinder.bind(key, params)
      } yield {
        valueStr match {
          case Right(valueStr) =>
            try {
              Right(codec.decode(valueStr))
            } catch {
              case t: Throwable => Left(s"Unable to bind from key: $key, error: ${t.getMessage}")
            }
          case _ => Left(s"Unable to bind from key: $key")
        }
      }

    override def unbind(key: String, value: T): String = stringBinder.unbind(key, codec.encode(value))
  }

  implicit def pathBindableParser[T](implicit stringBinder: PathBindable[String], codec: Codec[T]): PathBindable[T] = new PathBindable[T] {
    override def bind(key: String, value: String): Either[String, T] = {
      val valueStr = stringBinder.bind(key, value)
      valueStr match {
        case Right(valueStr) =>
          try {
            Right(codec.decode(valueStr))
          } catch {
            case t: Throwable => Left(s"Unable to bind from key: $key, error: ${t.getMessage}")
          }
        case _ => Left(s"Unable to bind from key: $key")
      }
    }

    override def unbind(key: String, value: T): String = stringBinder.unbind(key, codec.encode(value))
  }
}