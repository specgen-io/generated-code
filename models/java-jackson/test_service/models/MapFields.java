package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class MapFields {

	@JsonProperty(value = "int_map_field", required = true)
	private Map<String, Integer> intMapField;

	@JsonProperty(value = "string_map_field", required = true)
	private Map<String, String> stringMapField;

	@JsonCreator
	public MapFields(
		@JsonProperty(value = "int_map_field", required = true)
		Map<String, Integer> intMapField,
		@JsonProperty(value = "string_map_field", required = true)
		Map<String, String> stringMapField
	) {
		if (intMapField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.intMapField = intMapField;
		if (stringMapField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringMapField = stringMapField;
	}

	public Map<String, Integer> getIntMapField() {
		return intMapField;
	}

	public void setIntMapField(Map<String, Integer> intMapField) {
		this.intMapField = intMapField;
	}

	public Map<String, String> getStringMapField() {
		return stringMapField;
	}

	public void setStringMapField(Map<String, String> stringMapField) {
		this.stringMapField = stringMapField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MapFields)) return false;
		MapFields that = (MapFields) o;
		return Objects.equals(getIntMapField(), that.getIntMapField()) && Objects.equals(getStringMapField(), that.getStringMapField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIntMapField(), getStringMapField());
	}

	@Override
	public String toString() {
		return String.format("MapFields{intMapField=%s, stringMapField=%s}", intMapField, stringMapField);
	}
}
