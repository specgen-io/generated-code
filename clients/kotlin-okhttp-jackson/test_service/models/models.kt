package test_service.models

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonSubTypes.*
import com.fasterxml.jackson.core.type.*
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.math.BigDecimal
import java.time.*
import java.util.*
import java.io.*

data class Message(
	@JsonProperty(value = "int_field", required = true)
	val intField: Int,
	@JsonProperty(value = "string_field", required = true)
	val stringField: String,
)

enum class Choice {
	@JsonProperty("FIRST_CHOICE") FIRST_CHOICE,
	@JsonProperty("SECOND_CHOICE") SECOND_CHOICE,
	@JsonProperty("THIRD_CHOICE") THIRD_CHOICE,
}

data class Parameters(
	@JsonProperty(value = "int_field", required = true)
	val intField: Int,
	@JsonProperty(value = "long_field", required = true)
	val longField: Long,
	@JsonProperty(value = "float_field", required = true)
	val floatField: Float,
	@JsonProperty(value = "double_field", required = true)
	val doubleField: Double,
	@JsonProperty(value = "decimal_field", required = true)
	val decimalField: BigDecimal,
	@JsonProperty(value = "bool_field", required = true)
	val boolField: Boolean,
	@JsonProperty(value = "string_field", required = true)
	val stringField: String,
	@JsonProperty(value = "string_opt_field", required = false)
	val stringOptField: String?,
	@JsonProperty(value = "string_defaulted_field", required = true)
	val stringDefaultedField: String,
	@JsonProperty(value = "string_array_field", required = true)
	val stringArrayField: List<String>,
	@JsonProperty(value = "uuid_field", required = true)
	val uuidField: UUID,
	@JsonProperty(value = "date_field", required = true)
	val dateField: LocalDate,
	@JsonProperty(value = "date_array_field", required = true)
	val dateArrayField: List<LocalDate>,
	@JsonProperty(value = "datetime_field", required = true)
	val datetimeField: LocalDateTime,
	@JsonProperty(value = "enum_field", required = true)
	val enumField: Choice,
)

data class UrlParameters(
	@JsonProperty(value = "int_field", required = true)
	val intField: Int,
	@JsonProperty(value = "long_field", required = true)
	val longField: Long,
	@JsonProperty(value = "float_field", required = true)
	val floatField: Float,
	@JsonProperty(value = "double_field", required = true)
	val doubleField: Double,
	@JsonProperty(value = "decimal_field", required = true)
	val decimalField: BigDecimal,
	@JsonProperty(value = "bool_field", required = true)
	val boolField: Boolean,
	@JsonProperty(value = "string_field", required = true)
	val stringField: String,
	@JsonProperty(value = "uuid_field", required = true)
	val uuidField: UUID,
	@JsonProperty(value = "date_field", required = true)
	val dateField: LocalDate,
	@JsonProperty(value = "datetime_field", required = true)
	val datetimeField: LocalDateTime,
	@JsonProperty(value = "enum_field", required = true)
	val enumField: Choice,
)

data class Everything(
	@JsonProperty(value = "body_field", required = true)
	val bodyField: Message,
	@JsonProperty(value = "float_query", required = true)
	val floatQuery: Float,
	@JsonProperty(value = "bool_query", required = true)
	val boolQuery: Boolean,
	@JsonProperty(value = "uuid_header", required = true)
	val uuidHeader: UUID,
	@JsonProperty(value = "datetime_header", required = true)
	val datetimeHeader: LocalDateTime,
	@JsonProperty(value = "date_url", required = true)
	val dateUrl: LocalDate,
	@JsonProperty(value = "decimal_url", required = true)
	val decimalUrl: BigDecimal,
)

data class OkResult(
	@JsonProperty(value = "ok_result", required = true)
	val okResult: String,
)

data class CreatedResult(
	@JsonProperty(value = "created_result", required = true)
	val createdResult: String,
)

data class AcceptedResult(
	@JsonProperty(value = "accepted_result", required = true)
	val acceptedResult: String,
)

enum class ErrorLocation {
	@JsonProperty("query") QUERY,
	@JsonProperty("header") HEADER,
	@JsonProperty("body") BODY,
	@JsonProperty("unknown") UNKNOWN,
}

data class ValidationError(
	@JsonProperty(value = "path", required = true)
	val path: String,
	@JsonProperty(value = "code", required = true)
	val code: String,
	@JsonProperty(value = "message", required = false)
	val message: String?,
)

data class BadRequestError(
	@JsonProperty(value = "message", required = true)
	val message: String,
	@JsonProperty(value = "location", required = true)
	val location: ErrorLocation,
	@JsonProperty(value = "errors", required = true)
	val errors: List<ValidationError>,
)

data class NotFoundError(
	@JsonProperty(value = "message", required = true)
	val message: String,
)

data class InternalServerError(
	@JsonProperty(value = "message", required = true)
	val message: String,
)
