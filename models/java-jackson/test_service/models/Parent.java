package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class Parent {

	@JsonProperty(value = "field", required = true)
	private String field;

	@JsonProperty(value = "nested", required = true)
	private Message nested;

	@JsonCreator
	public Parent(
		@JsonProperty(value = "field", required = true)
		String field,
		@JsonProperty(value = "nested", required = true)
		Message nested
	) {
		if (field == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.field = field;
		if (nested == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.nested = nested;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Message getNested() {
		return nested;
	}

	public void setNested(Message nested) {
		this.nested = nested;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Parent)) return false;
		Parent that = (Parent) o;
		return Objects.equals(getField(), that.getField()) && Objects.equals(getNested(), that.getNested());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getField(), getNested());
	}

	@Override
	public String toString() {
		return String.format("Parent{field=%s, nested=%s}", field, nested);
	}
}
