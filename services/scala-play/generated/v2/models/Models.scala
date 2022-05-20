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

case class InternalServerError(
  @JsonKey("message") message: String
)

object InternalServerError {
  implicit val config = Configuration.default
  implicit val codec: Codec[InternalServerError] = deriveConfiguredCodec
}

case class ParamMessage(
  @JsonKey("name") name: String,
  @JsonKey("message") message: String
)

object ParamMessage {
  implicit val config = Configuration.default
  implicit val codec: Codec[ParamMessage] = deriveConfiguredCodec
}

case class BadRequestError(
  @JsonKey("message") message: String,
  @JsonKey("params") params: List[ParamMessage]
)

object BadRequestError {
  implicit val config = Configuration.default
  implicit val codec: Codec[BadRequestError] = deriveConfiguredCodec
}
