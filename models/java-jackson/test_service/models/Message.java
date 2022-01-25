package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class Message {

	@JsonProperty(value = "field", required = true)
	private int field;

	@JsonCreator
	public Message(
		@JsonProperty(value = "field", required = true)
		int field
	) {
		this.field = field;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message that = (Message) o;
		return getField() == that.getField();
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
