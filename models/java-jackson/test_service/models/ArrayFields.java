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
	private int[] intArrayField;

	@JsonProperty(value = "string_array_field", required = true)
	private String[] stringArrayField;

	@JsonCreator
	public ArrayFields(
		@JsonProperty(value = "int_array_field", required = true)
		int[] intArrayField,
		@JsonProperty(value = "string_array_field", required = true)
		String[] stringArrayField
	) {
		if (intArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.intArrayField = intArrayField;
		if (stringArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
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
