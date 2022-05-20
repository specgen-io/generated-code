package test_service.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class ParamMessage {

	@Json(name = "name")
	private String name;

	@Json(name = "message")
	private String message;

	public ParamMessage(String name, String message) {
		this.name = name;
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
