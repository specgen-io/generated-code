package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class MessageCases {

	@JsonProperty(value = "snake_case", required = true)
	private String snakeCase;

	@JsonProperty(value = "camelCase", required = true)
	private String camelCase;

	@JsonCreator
	public MessageCases(
		@JsonProperty(value = "snake_case", required = true)
		String snakeCase,
		@JsonProperty(value = "camelCase", required = true)
		String camelCase
	) {
		if (snakeCase == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.snakeCase = snakeCase;
		if (camelCase == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.camelCase = camelCase;
	}

	public String getSnakeCase() {
		return snakeCase;
	}

	public void setSnakeCase(String snakeCase) {
		this.snakeCase = snakeCase;
	}

	public String getCamelCase() {
		return camelCase;
	}

	public void setCamelCase(String camelCase) {
		this.camelCase = camelCase;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MessageCases)) return false;
		MessageCases that = (MessageCases) o;
		return Objects.equals(getSnakeCase(), that.getSnakeCase()) && Objects.equals(getCamelCase(), that.getCamelCase());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSnakeCase(), getCamelCase());
	}

	@Override
	public String toString() {
		return String.format("MessageCases{snakeCase=%s, camelCase=%s}", snakeCase, camelCase);
	}
}
