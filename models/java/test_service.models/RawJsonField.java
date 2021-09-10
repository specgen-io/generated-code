package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class RawJsonField {
	@JsonProperty("json_field")
	@JsonRawValue
	private Object jsonField;

	public RawJsonField() {
	}

	public RawJsonField(String jsonField) {
		this.jsonField = jsonField;
	}

	public String getJsonField() {
		return  jsonField == null ? null : jsonField.toString();
	}

	public void setJsonField(JsonNode node) {
		this.jsonField = node;
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
