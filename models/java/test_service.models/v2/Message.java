package test_service.models.v2;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Message {
	@JsonProperty("field")
	private String field;

	public Message() {
	}

	public Message(String field) {
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
		Message message = (Message) o;
		return Objects.equals(getField(), message.getField());
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
