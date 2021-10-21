package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Nested {
	@JsonCreator
	public Nested(
		@JsonProperty(value = "field", required = true)
		String field
	) {
		if (field == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.field = field;
	}

	@JsonProperty(value = "field", required = true)
	private String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		if (field == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.field = field;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Nested)) return false;
		Nested that = (Nested) o;
		return Objects.equals(getField(), that.getField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getField());
	}

	@Override
	public String toString() {
		return String.format("Nested{field=%s}", field);
	}
}
