package test_service.v2.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class Message {

	@Json(name = "field")
	private String field;

	public Message(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message that = (Message) o;
		return Objects.equals(getField(), that.getField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getField());
	}

	@Override
	public String toString() {
		return String.format("Message{field=%s}", field);
	}
}
