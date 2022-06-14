package test_service.v2.models

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
	@JsonProperty(value = "bool_field", required = true)
	val boolField: Boolean,
	@JsonProperty(value = "string_field", required = true)
	val stringField: String,
)

enum class ErrorLocation {
	@JsonProperty("query") QUERY,
	@JsonProperty("header") HEADER,
	@JsonProperty("body") BODY,
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
	@JsonProperty(value = "errors", required = false)
	val errors: List<ValidationError>?,
)

data class NotFoundError(
	@JsonProperty(value = "message", required = true)
	val message: String,
)

data class InternalServerError(
	@JsonProperty(value = "message", required = true)
	val message: String,
)
