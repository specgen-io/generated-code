package testservice.models

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.Codec
import io.circe.generic.extras.{Configuration, JsonKey}
import io.circe.generic.extras.semiauto.{deriveConfiguredCodec, deriveUnwrappedCodec}

case class Message(
  @JsonKey("field") field: Int
)

object Message {
  implicit val config = Configuration.default
  implicit val codec: Codec[Message] = deriveConfiguredCodec
}

case class Nested(
  @JsonKey("field") field: String
)

object Nested {
  implicit val config = Configuration.default
  implicit val codec: Codec[Nested] = deriveConfiguredCodec
}

case class Parent(
  @JsonKey("field") field: String,
  @JsonKey("nested") nested: Nested
)

object Parent {
  implicit val config = Configuration.default
  implicit val codec: Codec[Parent] = deriveConfiguredCodec
}

sealed abstract class Choice(val value: String) extends StringEnumEntry

case object Choice extends StringEnum[Choice] with StringCirceEnum[Choice] {
  case object FirstChoice extends Choice("FIRST_CHOICE")
  case object SecondChoice extends Choice("SECOND_CHOICE")
  case object ThirdChoice extends Choice("THIRD_CHOICE")
  val values = findValues
}

case class EnumFields(
  @JsonKey("enum_field") enumField: Choice
)

object EnumFields {
  implicit val config = Configuration.default
  implicit val codec: Codec[EnumFields] = deriveConfiguredCodec
}

case class NumericFields(
  @JsonKey("int_field") intField: Int,
  @JsonKey("long_field") longField: Long,
  @JsonKey("float_field") floatField: Float,
  @JsonKey("double_field") doubleField: Double,
  @JsonKey("decimal_field") decimalField: BigDecimal
)

object NumericFields {
  implicit val config = Configuration.default
  implicit val codec: Codec[NumericFields] = deriveConfiguredCodec
}

case class NonNumericFields(
  @JsonKey("boolean_field") booleanField: Boolean,
  @JsonKey("string_field") stringField: String,
  @JsonKey("uuid_field") uuidField: java.util.UUID,
  @JsonKey("date_field") dateField: java.time.LocalDate,
  @JsonKey("datetime_field") datetimeField: java.time.LocalDateTime
)

object NonNumericFields {
  implicit val config = Configuration.default
  implicit val codec: Codec[NonNumericFields] = deriveConfiguredCodec
}

case class ArrayFields(
  @JsonKey("int_array_field") intArrayField: List[Int],
  @JsonKey("string_array_field") stringArrayField: List[String]
)

object ArrayFields {
  implicit val config = Configuration.default
  implicit val codec: Codec[ArrayFields] = deriveConfiguredCodec
}

case class MapFields(
  @JsonKey("int_map_field") intMapField: Map[String, Int],
  @JsonKey("string_map_field") stringMapField: Map[String, String]
)

object MapFields {
  implicit val config = Configuration.default
  implicit val codec: Codec[MapFields] = deriveConfiguredCodec
}

case class OptionalFields(
  @JsonKey("int_option_field") intOptionField: Option[Int],
  @JsonKey("string_option_field") stringOptionField: Option[String]
)

object OptionalFields {
  implicit val config = Configuration.default
  implicit val codec: Codec[OptionalFields] = deriveConfiguredCodec
}

case class RawJsonField(
  @JsonKey("json_field") jsonField: io.circe.Json
)

object RawJsonField {
  implicit val config = Configuration.default
  implicit val codec: Codec[RawJsonField] = deriveConfiguredCodec
}

case class OrderCreated(
  @JsonKey("id") id: java.util.UUID,
  @JsonKey("sku") sku: String,
  @JsonKey("quantity") quantity: Int
)

object OrderCreated {
  implicit val config = Configuration.default
  implicit val codec: Codec[OrderCreated] = deriveConfiguredCodec
}

case class OrderChanged(
  @JsonKey("id") id: java.util.UUID,
  @JsonKey("quantity") quantity: Int
)

object OrderChanged {
  implicit val config = Configuration.default
  implicit val codec: Codec[OrderChanged] = deriveConfiguredCodec
}

case class OrderCanceled(
  @JsonKey("id") id: java.util.UUID
)

object OrderCanceled {
  implicit val config = Configuration.default
  implicit val codec: Codec[OrderCanceled] = deriveConfiguredCodec
}

sealed trait OrderEvent

object OrderEvent {
  case class Created(data: testservice.models.OrderCreated) extends OrderEvent
  case class Changed(data: testservice.models.OrderChanged) extends OrderEvent
  case class Canceled(data: testservice.models.OrderCanceled) extends OrderEvent
  implicit val config = Configuration.default.withSnakeCaseConstructorNames
  implicit val codec: Codec[OrderEvent] = deriveConfiguredCodec
  implicit val codecCreated: Codec[OrderEvent.Created] = deriveUnwrappedCodec
  implicit val codecChanged: Codec[OrderEvent.Changed] = deriveUnwrappedCodec
  implicit val codecCanceled: Codec[OrderEvent.Canceled] = deriveUnwrappedCodec
}

sealed trait OrderEventDiscriminated

object OrderEventDiscriminated {
  case class Created(data: testservice.models.OrderCreated) extends OrderEventDiscriminated
  case class Changed(data: testservice.models.OrderChanged) extends OrderEventDiscriminated
  case class Canceled(data: testservice.models.OrderCanceled) extends OrderEventDiscriminated
  implicit val config = Configuration.default.withSnakeCaseConstructorNames
  implicit val codec: Codec[OrderEventDiscriminated] = deriveConfiguredCodec
  implicit val codecCreated: Codec[OrderEventDiscriminated.Created] = deriveUnwrappedCodec
  implicit val codecChanged: Codec[OrderEventDiscriminated.Changed] = deriveUnwrappedCodec
  implicit val codecCanceled: Codec[OrderEventDiscriminated.Canceled] = deriveUnwrappedCodec
}

case class MessageCamelCase(
  @JsonKey("fieldInt") fieldInt: Int
)

object MessageCamelCase {
  implicit val config = Configuration.default
  implicit val codec: Codec[MessageCamelCase] = deriveConfiguredCodec
}

sealed trait OrderEventCamelCase

object OrderEventCamelCase {
  case class CreatedOrder(data: testservice.models.OrderCreated) extends OrderEventCamelCase
  case class ChangedOrder(data: testservice.models.OrderChanged) extends OrderEventCamelCase
  case class CanceledOrder(data: testservice.models.OrderCanceled) extends OrderEventCamelCase
  implicit val config = Configuration.default.withSnakeCaseConstructorNames
  implicit val codec: Codec[OrderEventCamelCase] = deriveConfiguredCodec
  implicit val codecCreatedOrder: Codec[OrderEventCamelCase.CreatedOrder] = deriveUnwrappedCodec
  implicit val codecChangedOrder: Codec[OrderEventCamelCase.ChangedOrder] = deriveUnwrappedCodec
  implicit val codecCanceledOrder: Codec[OrderEventCamelCase.CanceledOrder] = deriveUnwrappedCodec
}
