package test_service.v2.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class Message {

	@JsonProperty(value = "field", required = true)
	private String field;

	@JsonCreator
	public Message(
		@JsonProperty(value = "field", required = true)
		String field
	) {
		if (field == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message that = (Message) o;
		return Objects.equals(getField(), that.getField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getField());
	}

	@Override
	public String toString() {
		return String.format("Message{field=%s}", field);
	}
}
