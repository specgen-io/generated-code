package testservice.models

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.Codec
import io.circe.generic.extras.{Configuration, JsonKey}
import io.circe.generic.extras.semiauto.{deriveConfiguredCodec, deriveUnwrappedCodec}
import testservice.taggedunion._

case class Message(
  @JsonKey("int_field") intField: Int,
  @JsonKey("string_field") stringField: String
)

object Message {
  implicit val config = Configuration.default
  implicit val codec: Codec[Message] = deriveConfiguredCodec
}

sealed abstract class Choice(val value: String) extends StringEnumEntry

case object Choice extends StringEnum[Choice] with StringCirceEnum[Choice] {
  case object FirstChoice extends Choice("FIRST_CHOICE")
  case object SecondChoice extends Choice("SECOND_CHOICE")
  case object ThirdChoice extends Choice("THIRD_CHOICE")
  val values = findValues
}

case class Parameters(
  @JsonKey("int_field") intField: Int,
  @JsonKey("long_field") longField: Long,
  @JsonKey("float_field") floatField: Float,
  @JsonKey("double_field") doubleField: Double,
  @JsonKey("decimal_field") decimalField: BigDecimal,
  @JsonKey("bool_field") boolField: Boolean,
  @JsonKey("string_field") stringField: String,
  @JsonKey("string_opt_field") stringOptField: Option[String],
  @JsonKey("string_defaulted_field") stringDefaultedField: String,
  @JsonKey("string_array_field") stringArrayField: List[String],
  @JsonKey("uuid_field") uuidField: java.util.UUID,
  @JsonKey("date_field") dateField: java.time.LocalDate,
  @JsonKey("date_array_field") dateArrayField: List[java.time.LocalDate],
  @JsonKey("datetime_field") datetimeField: java.time.LocalDateTime,
  @JsonKey("enum_field") enumField: Choice
)

object Parameters {
  implicit val config = Configuration.default
  implicit val codec: Codec[Parameters] = deriveConfiguredCodec
}

case class UrlParameters(
  @JsonKey("int_field") intField: Int,
  @JsonKey("long_field") longField: Long,
  @JsonKey("float_field") floatField: Float,
  @JsonKey("double_field") doubleField: Double,
  @JsonKey("decimal_field") decimalField: BigDecimal,
  @JsonKey("bool_field") boolField: Boolean,
  @JsonKey("string_field") stringField: String,
  @JsonKey("uuid_field") uuidField: java.util.UUID,
  @JsonKey("date_field") dateField: java.time.LocalDate,
  @JsonKey("datetime_field") datetimeField: java.time.LocalDateTime,
  @JsonKey("enum_field") enumField: Choice
)

object UrlParameters {
  implicit val config = Configuration.default
  implicit val codec: Codec[UrlParameters] = deriveConfiguredCodec
}

case class Everything(
  @JsonKey("body_field") bodyField: Message,
  @JsonKey("float_query") floatQuery: Float,
  @JsonKey("bool_query") boolQuery: Boolean,
  @JsonKey("uuid_header") uuidHeader: java.util.UUID,
  @JsonKey("datetime_header") datetimeHeader: java.time.LocalDateTime,
  @JsonKey("date_url") dateUrl: java.time.LocalDate,
  @JsonKey("decimal_url") decimalUrl: BigDecimal
)

object Everything {
  implicit val config = Configuration.default
  implicit val codec: Codec[Everything] = deriveConfiguredCodec
}

case class OkResult(
  @JsonKey("ok_result") okResult: String
)

object OkResult {
  implicit val config = Configuration.default
  implicit val codec: Codec[OkResult] = deriveConfiguredCodec
}

case class CreatedResult(
  @JsonKey("created_result") createdResult: String
)

object CreatedResult {
  implicit val config = Configuration.default
  implicit val codec: Codec[CreatedResult] = deriveConfiguredCodec
}

case class AcceptedResult(
  @JsonKey("accepted_result") acceptedResult: String
)

object AcceptedResult {
  implicit val config = Configuration.default
  implicit val codec: Codec[AcceptedResult] = deriveConfiguredCodec
}

sealed abstract class ErrorLocation(val value: String) extends StringEnumEntry

case object ErrorLocation extends StringEnum[ErrorLocation] with StringCirceEnum[ErrorLocation] {
  case object Query extends ErrorLocation("query")
  case object Header extends ErrorLocation("header")
  case object Body extends ErrorLocation("body")
  val values = findValues
}

case class ValidationError(
  @JsonKey("path") path: String,
  @JsonKey("code") code: String,
  @JsonKey("message") message: Option[String]
)

object ValidationError {
  implicit val config = Configuration.default
  implicit val codec: Codec[ValidationError] = deriveConfiguredCodec
}

case class BadRequestError(
  @JsonKey("message") message: String,
  @JsonKey("location") location: ErrorLocation,
  @JsonKey("errors") errors: Option[List[ValidationError]]
)

object BadRequestError {
  implicit val config = Configuration.default
  implicit val codec: Codec[BadRequestError] = deriveConfiguredCodec
}

case class NotFoundError(
  @JsonKey("message") message: String
)

object NotFoundError {
  implicit val config = Configuration.default
  implicit val codec: Codec[NotFoundError] = deriveConfiguredCodec
}

case class InternalServerError(
  @JsonKey("message") message: String
)

object InternalServerError {
  implicit val config = Configuration.default
  implicit val codec: Codec[InternalServerError] = deriveConfiguredCodec
}
