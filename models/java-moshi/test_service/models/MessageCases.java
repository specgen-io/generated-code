package test_service.models;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;


public class MessageCases {

	@Json(name = "snake_case")
	private String snakeCase;

	@Json(name = "camelCase")
	private String camelCase;

	public MessageCases(String snakeCase, String camelCase) {
		this.snakeCase = snakeCase;
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
