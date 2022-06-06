package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class ValidationError {

	@JsonProperty(value = "path", required = true)
	private String path;

	@JsonProperty(value = "code", required = true)
	private String code;

	@JsonProperty(value = "message", required = false)
	private String message;

	@JsonCreator
	public ValidationError(
		@JsonProperty(value = "path", required = true)
		String path,
		@JsonProperty(value = "code", required = true)
		String code,
		@JsonProperty(value = "message", required = false)
		String message
	) {
		if (path == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.path = path;
		if (code == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.code = code;
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		if (!(o instanceof ValidationError)) return false;
		ValidationError that = (ValidationError) o;
		return Objects.equals(getPath(), that.getPath()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getMessage(), that.getMessage());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPath(), getCode(), getMessage());
	}

	@Override
	public String toString() {
		return String.format("ValidationError{path=%s, code=%s, message=%s}", path, code, message);
	}
}
