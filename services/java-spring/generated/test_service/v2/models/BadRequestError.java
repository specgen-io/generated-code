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

	@JsonProperty(value = "params", required = true)
	private ParamMessage[] params;

	@JsonCreator
	public BadRequestError(
		@JsonProperty(value = "message", required = true)
		String message,
		@JsonProperty(value = "params", required = true)
		ParamMessage[] params
	) {
		if (message == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.message = message;
		if (params == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.params = params;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ParamMessage[] getParams() {
		return params;
	}

	public void setParams(ParamMessage[] params) {
		this.params = params;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BadRequestError)) return false;
		BadRequestError that = (BadRequestError) o;
		return Objects.equals(getMessage(), that.getMessage()) && Arrays.equals(getParams(), that.getParams());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getMessage());
		result = 31 * result + Arrays.hashCode(getParams());
		return result;
	}

	@Override
	public String toString() {
		return String.format("BadRequestError{message=%s, params=%s}", message, Arrays.toString(params));
	}
}
