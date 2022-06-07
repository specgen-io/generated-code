package test_service.v2.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class InternalServerError {

	@JsonProperty(value = "message", required = true)
	private String message;

	@JsonCreator
	public InternalServerError(
		@JsonProperty(value = "message", required = true)
		String message
	) {
		if (message == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.message = message;
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
		if (!(o instanceof InternalServerError)) return false;
		InternalServerError that = (InternalServerError) o;
		return Objects.equals(getMessage(), that.getMessage());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMessage());
	}

	@Override
	public String toString() {
		return String.format("InternalServerError{message=%s}", message);
	}
}
