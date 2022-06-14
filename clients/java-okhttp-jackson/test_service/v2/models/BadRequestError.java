package test_service.v2.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class BadRequestError {

	@JsonProperty(value = "message", required = true)
	private String message;

	@JsonProperty(value = "location", required = true)
	private ErrorLocation location;

	@JsonProperty(value = "errors", required = false)
	private List<ValidationError> errors;

	@JsonCreator
	public BadRequestError(
		@JsonProperty(value = "message", required = true)
		String message,
		@JsonProperty(value = "location", required = true)
		ErrorLocation location,
		@JsonProperty(value = "errors", required = false)
		List<ValidationError> errors
	) {
		if (message == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.message = message;
		if (location == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.location = location;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorLocation getLocation() {
		return location;
	}

	public void setLocation(ErrorLocation location) {
		this.location = location;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BadRequestError)) return false;
		BadRequestError that = (BadRequestError) o;
		return Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getErrors(), that.getErrors());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMessage(), getLocation(), getErrors());
	}

	@Override
	public String toString() {
		return String.format("BadRequestError{message=%s, location=%s, errors=%s}", message, location, errors);
	}
}
