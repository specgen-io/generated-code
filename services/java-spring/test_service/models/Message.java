package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Message {
	@JsonProperty("int_field")
	private int intField;
	@JsonProperty("string_field")
	private String stringField;

	public Message() {
	}

	public Message(int intField, String stringField) {
		this.intField = intField;
		this.stringField = stringField;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int value) {
		this.intField = value;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String value) {
		this.stringField = value;
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
