package test_service.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class ArrayFields {

	@Json(name = "int_array_field")
	private List<Integer> intArrayField;

	@Json(name = "string_array_field")
	private List<String> stringArrayField;

	public ArrayFields(List<Integer> intArrayField, List<String> stringArrayField) {
		this.intArrayField = intArrayField;
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
