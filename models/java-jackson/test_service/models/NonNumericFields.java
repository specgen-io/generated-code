package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class NonNumericFields {

	@JsonProperty(value = "boolean_field", required = true)
	private boolean booleanField;

	@JsonProperty(value = "string_field", required = true)
	private String stringField;

	@JsonProperty(value = "uuid_field", required = true)
	private UUID uuidField;

	@JsonProperty(value = "date_field", required = true)
	private LocalDate dateField;

	@JsonProperty(value = "datetime_field", required = true)
	private LocalDateTime datetimeField;

	@JsonCreator
	public NonNumericFields(
		@JsonProperty(value = "boolean_field", required = true)
		boolean booleanField,
		@JsonProperty(value = "string_field", required = true)
		String stringField,
		@JsonProperty(value = "uuid_field", required = true)
		UUID uuidField,
		@JsonProperty(value = "date_field", required = true)
		LocalDate dateField,
		@JsonProperty(value = "datetime_field", required = true)
		LocalDateTime datetimeField
	) {
		this.booleanField = booleanField;
		if (stringField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringField = stringField;
		if (uuidField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.uuidField = uuidField;
		if (dateField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateField = dateField;
		if (datetimeField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.datetimeField = datetimeField;
	}

	public boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(boolean booleanField) {
		this.booleanField = booleanField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public UUID getUuidField() {
		return uuidField;
	}

	public void setUuidField(UUID uuidField) {
		this.uuidField = uuidField;
	}

	public LocalDate getDateField() {
		return dateField;
	}

	public void setDateField(LocalDate dateField) {
		this.dateField = dateField;
	}

	public LocalDateTime getDatetimeField() {
		return datetimeField;
	}

	public void setDatetimeField(LocalDateTime datetimeField) {
		this.datetimeField = datetimeField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NonNumericFields)) return false;
		NonNumericFields that = (NonNumericFields) o;
		return getBooleanField() == that.getBooleanField() && Objects.equals(getStringField(), that.getStringField()) && Objects.equals(getUuidField(), that.getUuidField()) && Objects.equals(getDateField(), that.getDateField()) && Objects.equals(getDatetimeField(), that.getDatetimeField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBooleanField(), getStringField(), getUuidField(), getDateField(), getDatetimeField());
	}

	@Override
	public String toString() {
		return String.format("NonNumericFields{booleanField=%s, stringField=%s, uuidField=%s, dateField=%s, datetimeField=%s}", booleanField, stringField, uuidField, dateField, datetimeField);
	}
}
