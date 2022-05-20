package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class ParamMessage {

	@JsonProperty(value = "name", required = true)
	private String name;

	@JsonProperty(value = "message", required = true)
	private String message;

	@JsonCreator
	public ParamMessage(
		@JsonProperty(value = "name", required = true)
		String name,
		@JsonProperty(value = "message", required = true)
		String message
	) {
		if (name == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.name = name;
		if (message == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ParamMessage)) return false;
		ParamMessage that = (ParamMessage) o;
		return Objects.equals(getName(), that.getName()) && Objects.equals(getMessage(), that.getMessage());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getMessage());
	}

	@Override
	public String toString() {
		return String.format("ParamMessage{name=%s, message=%s}", name, message);
	}
}
