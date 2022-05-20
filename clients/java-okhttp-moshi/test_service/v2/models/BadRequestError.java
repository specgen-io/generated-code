package test_service.v2.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class BadRequestError {

	@Json(name = "message")
	private String message;

	@Json(name = "params")
	private ParamMessage[] params;

	public BadRequestError(String message, ParamMessage[] params) {
		this.message = message;
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
