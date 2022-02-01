package test_service.models

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonSubTypes.*
import com.fasterxml.jackson.core.type.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.math.BigDecimal
import java.time.*
import java.util.*
import java.io.*

data class Message(
	@JsonProperty(value = "field", required = true)
	val field: Int,
)

data class MessageCases(
	@JsonProperty(value = "snake_case", required = true)
	val snakeCase: String,
	@JsonProperty(value = "camelCase", required = true)
	val camelCase: String,
)

data class Parent(
	@JsonProperty(value = "field", required = true)
	val field: String,
	@JsonProperty(value = "nested", required = true)
	val nested: Message,
)

enum class Choice {
	@JsonProperty("One") FIRST_CHOICE,
	@JsonProperty("Two") SECOND_CHOICE,
	@JsonProperty("Three") THIRD_CHOICE,
}

data class EnumFields(
	@JsonProperty(value = "enum_field", required = true)
	val enumField: Choice,
)

data class NumericFields(
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
)

data class NonNumericFields(
	@JsonProperty(value = "boolean_field", required = true)
	val booleanField: Boolean,
	@JsonProperty(value = "string_field", required = true)
	val stringField: String,
	@JsonProperty(value = "uuid_field", required = true)
	val uuidField: UUID,
	@JsonProperty(value = "date_field", required = true)
	val dateField: LocalDate,
	@JsonProperty(value = "datetime_field", required = true)
	val datetimeField: LocalDateTime,
)

data class ArrayFields(
	@JsonProperty(value = "int_array_field", required = true)
	val intArrayField: Array<Int>,
	@JsonProperty(value = "string_array_field", required = true)
	val stringArrayField: Array<String>,
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is ArrayFields) return false

		if (!intArrayField.contentEquals(other.intArrayField)) return false
		if (!stringArrayField.contentEquals(other.stringArrayField)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = intArrayField.contentHashCode()
		result = 31 * result + stringArrayField.contentHashCode()
		return result
	}
}

data class MapFields(
	@JsonProperty(value = "int_map_field", required = true)
	val intMapField: Map<String, Int>,
	@JsonProperty(value = "string_map_field", required = true)
	val stringMapField: Map<String, String>,
)

data class OptionalFields(
	@JsonProperty(value = "int_option_field", required = false)
	val intOptionField: Int?,
	@JsonProperty(value = "string_option_field", required = false)
	val stringOptionField: String?,
)

data class RawJsonField(
	@JsonProperty(value = "json_field", required = true)
	val jsonField: JsonNode,
)

data class OrderCreated(
	@JsonProperty(value = "id", required = true)
	val id: UUID,
	@JsonProperty(value = "sku", required = true)
	val sku: String,
	@JsonProperty(value = "quantity", required = true)
	val quantity: Int,
)

data class OrderChanged(
	@JsonProperty(value = "id", required = true)
	val id: UUID,
	@JsonProperty(value = "quantity", required = true)
	val quantity: Int,
)

data class OrderCanceled(
	@JsonProperty(value = "id", required = true)
	val id: UUID,
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
sealed interface OrderEventWrapper {
	@JsonTypeName("created")
	data class Created(@field:JsonIgnore val data: OrderCreated): OrderEventWrapper {
		@get:JsonUnwrapped
		private val _data: OrderCreated
			get() = data
	}
	@JsonTypeName("changed")
	data class Changed(@field:JsonIgnore val data: OrderChanged): OrderEventWrapper {
		@get:JsonUnwrapped
		private val _data: OrderChanged
			get() = data
	}
	@JsonTypeName("canceled")
	data class Canceled(@field:JsonIgnore val data: OrderCanceled): OrderEventWrapper {
		@get:JsonUnwrapped
		private val _data: OrderCanceled
			get() = data
	}
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type")
sealed interface OrderEventDiscriminator {
	@JsonTypeName("created")
	data class Created(@field:JsonIgnore val data: OrderCreated): OrderEventDiscriminator {
		@get:JsonUnwrapped
		private val _data: OrderCreated
			get() = data
	}
	@JsonTypeName("changed")
	data class Changed(@field:JsonIgnore val data: OrderChanged): OrderEventDiscriminator {
		@get:JsonUnwrapped
		private val _data: OrderChanged
			get() = data
	}
	@JsonTypeName("canceled")
	data class Canceled(@field:JsonIgnore val data: OrderCanceled): OrderEventDiscriminator {
		@get:JsonUnwrapped
		private val _data: OrderCanceled
			get() = data
	}
}
