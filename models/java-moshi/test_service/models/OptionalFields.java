package test_service.models;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class OptionalFields {

	@Json(name = "int_option_field")
	private Integer intOptionField;

	@Json(name = "string_option_field")
	private String stringOptionField;

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
		OptionalFields that = (OptionalFields) o;
		return Objects.equals(getIntOptionField(), that.getIntOptionField()) && Objects.equals(getStringOptionField(), that.getStringOptionField());
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
