package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class RawJsonField {

	@JsonProperty(value = "json_field", required = true)
	private JsonNode jsonField;

	@JsonCreator
	public RawJsonField(
		@JsonProperty(value = "json_field", required = true)
		JsonNode jsonField
	) {
		if (jsonField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.jsonField = jsonField;
	}

	public JsonNode getJsonField() {
		return jsonField;
	}

	public void setJsonField(JsonNode jsonField) {
		this.jsonField = jsonField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RawJsonField)) return false;
		RawJsonField that = (RawJsonField) o;
		return Objects.equals(getJsonField(), that.getJsonField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getJsonField());
	}

	@Override
	public String toString() {
		return String.format("RawJsonField{jsonField=%s}", jsonField);
	}
}
