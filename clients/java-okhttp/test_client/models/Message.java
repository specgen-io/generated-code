package test_client.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Message {
	@JsonCreator
	public Message(
		@JsonProperty(value = "int_field", required = true)
		int intField,
		@JsonProperty(value = "string_field", required = true)
		String stringField
	) {
		this.intField = intField;
		if (stringField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringField = stringField;
	}

	@JsonProperty(value = "int_field", required = true)
	private int intField;

	@JsonProperty(value = "string_field", required = true)
	private String stringField;

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		if (stringField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringField = stringField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message that = (Message) o;
		return getIntField() == that.getIntField() && Objects.equals(getStringField(), that.getStringField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIntField(), getStringField());
	}

	@Override
	public String toString() {
		return String.format("Message{intField=%s, stringField=%s}", intField, stringField);
	}
}
