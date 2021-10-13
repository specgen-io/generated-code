package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class ArrayFields {
	public ArrayFields() {
	}

	public ArrayFields(int[] intArrayField, String[] stringArrayField) {
		if (intArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.intArrayField = intArrayField;
		if (stringArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringArrayField = stringArrayField;
	}

	@JsonProperty("int_array_field")
	private int[] intArrayField;

	@JsonProperty("string_array_field")
	private String[] stringArrayField;

	public int[] getIntArrayField() {
		return intArrayField;
	}

	public void setIntArrayField(int[] intArrayField) {
		if (intArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.intArrayField = intArrayField;
	}

	public String[] getStringArrayField() {
		return stringArrayField;
	}

	public void setStringArrayField(String[] stringArrayField) {
		if (stringArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringArrayField = stringArrayField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ArrayFields)) return false;
		ArrayFields that = (ArrayFields) o;
		return Arrays.equals(getIntArrayField(), that.getIntArrayField()) && Arrays.equals(getStringArrayField(), that.getStringArrayField());
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getIntArrayField());
		result = 31 * result + Arrays.hashCode(getStringArrayField());
		return result;
	}

	@Override
	public String toString() {
		return String.format("ArrayFields{intArrayField=%s, stringArrayField=%s}", Arrays.toString(intArrayField), Arrays.toString(stringArrayField));
	}
}
