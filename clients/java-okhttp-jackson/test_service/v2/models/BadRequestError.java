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
	private ValidationError[] errors;

	@JsonCreator
	public BadRequestError(
		@JsonProperty(value = "message", required = true)
		String message,
		@JsonProperty(value = "location", required = true)
		ErrorLocation location,
		@JsonProperty(value = "errors", required = false)
		ValidationError[] errors
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

	public ValidationError[] getErrors() {
		return errors;
	}

	public void setErrors(ValidationError[] errors) {
		this.errors = errors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BadRequestError)) return false;
		BadRequestError that = (BadRequestError) o;
		return Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getLocation(), that.getLocation()) && Arrays.equals(getErrors(), that.getErrors());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getMessage(), getLocation());
		result = 31 * result + Arrays.hashCode(getErrors());
		return result;
	}

	@Override
	public String toString() {
		return String.format("BadRequestError{message=%s, location=%s, errors=%s}", message, location, Arrays.toString(errors));
	}
}
