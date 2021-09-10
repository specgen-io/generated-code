package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Parent {
	@JsonProperty("field")
	private String field;
	@JsonProperty("nested")
	private Nested nested;

	public Parent() {
	}

	public Parent(String field, Nested nested) {
		this.field = field;
		this.nested = nested;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Nested getNested() {
		return nested;
	}

	public void setNested(Nested nested) {
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
