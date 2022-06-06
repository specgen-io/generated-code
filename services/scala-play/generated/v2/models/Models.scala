package v2.models

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.Codec
import io.circe.generic.extras.{Configuration, JsonKey}
import io.circe.generic.extras.semiauto.{deriveConfiguredCodec, deriveUnwrappedCodec}
import json.taggedunion._

case class Message(
  @JsonKey("bool_field") boolField: Boolean,
  @JsonKey("string_field") stringField: String
)

object Message {
  implicit val config = Configuration.default
  implicit val codec: Codec[Message] = deriveConfiguredCodec
}

sealed abstract class ErrorLocation(val value: String) extends StringEnumEntry

case object ErrorLocation extends StringEnum[ErrorLocation] with StringCirceEnum[ErrorLocation] {
  case object Query extends ErrorLocation("query")
  case object Header extends ErrorLocation("header")
  case object Body extends ErrorLocation("body")
  case object Unknown extends ErrorLocation("unknown")
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
  @JsonKey("errors") errors: List[ValidationError]
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
