package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class RawJsonField {
	public RawJsonField() {
	}

	public RawJsonField(JsonNode jsonField) {
		if (jsonField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.jsonField = jsonField;
	}

	@JsonProperty("json_field")
	private JsonNode jsonField;

	public JsonNode getJsonField() {
		return jsonField;
	}

	public void setJsonField(JsonNode jsonField) {
		if (jsonField == null) { throw new IllegalArgumentException("null value is not allowed"); }
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
