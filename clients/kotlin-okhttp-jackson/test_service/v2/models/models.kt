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

data class InternalServerError(
	@JsonProperty(value = "message", required = true)
	val message: String,
)

data class ParamMessage(
	@JsonProperty(value = "name", required = true)
	val name: String,
	@JsonProperty(value = "message", required = true)
	val message: String,
)

data class BadRequestError(
	@JsonProperty(value = "message", required = true)
	val message: String,
	@JsonProperty(value = "params", required = true)
	val params: Array<ParamMessage>,
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is BadRequestError) return false

		if (message != other.message) return false
		if (!params.contentEquals(other.params)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = params.contentHashCode()
		result = 31 * result + message.hashCode()
		return result
	}
}
