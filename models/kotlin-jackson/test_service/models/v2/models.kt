package test_service.models.v2

import java.time.*
import java.util.*
import java.math.BigDecimal
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.annotation.*

data class Message(
	@JsonProperty(value = "field", required = true)
	val field: String,
)
