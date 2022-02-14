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
	val stringArrayField: Array<String>,
	@JsonProperty(value = "uuid_field", required = true)
	val uuidField: UUID,
	@JsonProperty(value = "date_field", required = true)
	val dateField: LocalDate,
	@JsonProperty(value = "date_array_field", required = true)
	val dateArrayField: Array<LocalDate>,
	@JsonProperty(value = "datetime_field", required = true)
	val datetimeField: LocalDateTime,
	@JsonProperty(value = "enum_field", required = true)
	val enumField: Choice,
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Parameters) return false

		if (intField != other.intField) return false
		if (longField != other.longField) return false
		if (floatField != other.floatField) return false
		if (doubleField != other.doubleField) return false
		if (decimalField != other.decimalField) return false
		if (boolField != other.boolField) return false
		if (stringField != other.stringField) return false
		if (stringOptField != other.stringOptField) return false
		if (stringDefaultedField != other.stringDefaultedField) return false
		if (!stringArrayField.contentEquals(other.stringArrayField)) return false
		if (uuidField != other.uuidField) return false
		if (dateField != other.dateField) return false
		if (!dateArrayField.contentEquals(other.dateArrayField)) return false
		if (datetimeField != other.datetimeField) return false
		if (enumField != other.enumField) return false

		return true
	}

	override fun hashCode(): Int {
		var result = stringArrayField.contentHashCode()
		result = 31 * result + dateArrayField.contentHashCode()
		result = 31 * result + intField.hashCode()
		result = 31 * result + longField.hashCode()
		result = 31 * result + floatField.hashCode()
		result = 31 * result + doubleField.hashCode()
		result = 31 * result + decimalField.hashCode()
		result = 31 * result + boolField.hashCode()
		result = 31 * result + stringField.hashCode()
		result = 31 * result + (stringOptField?.hashCode() ?: 0)
		result = 31 * result + stringDefaultedField.hashCode()
		result = 31 * result + uuidField.hashCode()
		result = 31 * result + dateField.hashCode()
		result = 31 * result + datetimeField.hashCode()
		result = 31 * result + enumField.hashCode()
		return result
	}
}

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
