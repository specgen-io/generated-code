package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class ArrayFields {

	@JsonProperty(value = "int_array_field", required = true)
	private List<Integer> intArrayField;

	@JsonProperty(value = "string_array_field", required = true)
	private List<String> stringArrayField;

	@JsonCreator
	public ArrayFields(
		@JsonProperty(value = "int_array_field", required = true)
		List<Integer> intArrayField,
		@JsonProperty(value = "string_array_field", required = true)
		List<String> stringArrayField
	) {
		if (intArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.intArrayField = intArrayField;
		if (stringArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringArrayField = stringArrayField;
	}

	public List<Integer> getIntArrayField() {
		return intArrayField;
	}

	public void setIntArrayField(List<Integer> intArrayField) {
		this.intArrayField = intArrayField;
	}

	public List<String> getStringArrayField() {
		return stringArrayField;
	}

	public void setStringArrayField(List<String> stringArrayField) {
		this.stringArrayField = stringArrayField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ArrayFields)) return false;
		ArrayFields that = (ArrayFields) o;
		return Objects.equals(getIntArrayField(), that.getIntArrayField()) && Objects.equals(getStringArrayField(), that.getStringArrayField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIntArrayField(), getStringArrayField());
	}

	@Override
	public String toString() {
		return String.format("ArrayFields{intArrayField=%s, stringArrayField=%s}", intArrayField, stringArrayField);
	}
}
