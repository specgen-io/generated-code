package test_service.models;

import com.squareup.moshi.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class Parent {

	@Json(name = "field")
	private String field;

	@Json(name = "nested")
	private Message nested;

	public Parent(String field, Message nested) {
		this.field = field;
		this.nested = nested;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Message getNested() {
		return nested;
	}

	public void setNested(Message nested) {
		this.nested = nested;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Parent)) return false;
		Parent that = (Parent) o;
		return Objects.equals(getField(), that.getField()) && Objects.equals(getNested(), that.getNested());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getField(), getNested());
	}

	@Override
	public String toString() {
		return String.format("Parent{field=%s, nested=%s}", field, nested);
	}
}
