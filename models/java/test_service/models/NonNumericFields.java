package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class NonNumericFields {
	public NonNumericFields() {
	}

	public NonNumericFields(boolean booleanField, String stringField, UUID uuidField, LocalDate dateField, LocalDateTime datetimeField) {
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

	@JsonProperty("boolean_field")
	private boolean booleanField;

	@JsonProperty("string_field")
	private String stringField;

	@JsonProperty("uuid_field")
	private UUID uuidField;

	@JsonProperty("date_field")
	private LocalDate dateField;

	@JsonProperty("datetime_field")
	private LocalDateTime datetimeField;

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
		if (stringField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringField = stringField;
	}

	public UUID getUuidField() {
		return uuidField;
	}

	public void setUuidField(UUID uuidField) {
		if (uuidField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.uuidField = uuidField;
	}

	public LocalDate getDateField() {
		return dateField;
	}

	public void setDateField(LocalDate dateField) {
		if (dateField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateField = dateField;
	}

	public LocalDateTime getDatetimeField() {
		return datetimeField;
	}

	public void setDatetimeField(LocalDateTime datetimeField) {
		if (datetimeField == null) { throw new IllegalArgumentException("null value is not allowed"); }
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
