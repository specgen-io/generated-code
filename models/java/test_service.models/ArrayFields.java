package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class ArrayFields {
	@JsonProperty("int_array_field")
	private int[] intArrayField;
	@JsonProperty("string_array_field")
	private String[] stringArrayField;

	public ArrayFields() {
	}

	public ArrayFields(int[] intArrayField, String[] stringArrayField) {
		this.intArrayField = intArrayField;
		this.stringArrayField = stringArrayField;
	}

	public int[] getIntArrayField() {
		return intArrayField;
	}

	public void setIntArrayField(int[] intArrayField) {
		this.intArrayField = intArrayField;
	}

	public String[] getStringArrayField() {
		return stringArrayField;
	}

	public void setStringArrayField(String[] stringArrayField) {
		this.stringArrayField = stringArrayField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ArrayFields)) return false;
		ArrayFields arrayFields = (ArrayFields) o;
		return Arrays.equals(getIntArrayField(), arrayFields.getIntArrayField()) && Arrays.equals(getStringArrayField(), arrayFields.getStringArrayField());
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
