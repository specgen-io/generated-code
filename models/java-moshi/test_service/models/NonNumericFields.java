package test_service.models;

import com.squareup.moshi.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class NonNumericFields {

	@Json(name = "boolean_field")
	private boolean booleanField;

	@Json(name = "string_field")
	private String stringField;

	@Json(name = "uuid_field")
	private UUID uuidField;

	@Json(name = "date_field")
	private LocalDate dateField;

	@Json(name = "datetime_field")
	private LocalDateTime datetimeField;

	public NonNumericFields(boolean booleanField, String stringField, UUID uuidField, LocalDate dateField, LocalDateTime datetimeField) {
		this.booleanField = booleanField;
		this.stringField = stringField;
		this.uuidField = uuidField;
		this.dateField = dateField;
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
