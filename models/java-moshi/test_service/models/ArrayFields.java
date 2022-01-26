package test_service.models;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;


public class ArrayFields {

	@Json(name = "int_array_field")
	private int[] intArrayField;

	@Json(name = "string_array_field")
	private String[] stringArrayField;

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
