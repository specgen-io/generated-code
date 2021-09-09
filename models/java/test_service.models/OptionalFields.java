package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OptionalFields {
	@JsonProperty("int_option_field")
	private Integer intOptionField;
	@JsonProperty("string_option_field")
	private String stringOptionField;

	public OptionalFields() {
	}

	public OptionalFields(Integer intOptionField, String stringOptionField) {
		this.intOptionField = intOptionField;
		this.stringOptionField = stringOptionField;
	}

	public Integer getIntOptionField() {
		return intOptionField;
	}

	public void setIntOptionField(Integer intOptionField) {
		this.intOptionField = intOptionField;
	}

	public String getStringOptionField() {
		return stringOptionField;
	}

	public void setStringOptionField(String stringOptionField) {
		this.stringOptionField = stringOptionField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OptionalFields)) return false;
		OptionalFields optionalFields = (OptionalFields) o;
		return Objects.equals(getIntOptionField(), optionalFields.getIntOptionField()) && Objects.equals(getStringOptionField(), optionalFields.getStringOptionField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIntOptionField(), getStringOptionField());
	}

	@Override
	public String toString() {
		return String.format("OptionalFields{intOptionField=%s, stringOptionField=%s}", intOptionField, stringOptionField);
	}
}
