package test_service.v2.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Message {
	@JsonCreator
	public Message(
		@JsonProperty(value = "bool_field", required = true)
		boolean boolField,
		@JsonProperty(value = "string_field", required = true)
		String stringField
	) {
		this.boolField = boolField;
		if (stringField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringField = stringField;
	}

	@JsonProperty(value = "bool_field", required = true)
	private boolean boolField;

	@JsonProperty(value = "string_field", required = true)
	private String stringField;

	public boolean getBoolField() {
		return boolField;
	}

	public void setBoolField(boolean boolField) {
		this.boolField = boolField;
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
		return getBoolField() == that.getBoolField() && Objects.equals(getStringField(), that.getStringField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBoolField(), getStringField());
	}

	@Override
	public String toString() {
		return String.format("Message{boolField=%s, stringField=%s}", boolField, stringField);
	}
}
