package test_service.models.spec;

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
}
