package test_client.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class UrlParameters {
	@JsonCreator
	public UrlParameters(
		@JsonProperty(value = "int_field", required = true)
		int intField,
		@JsonProperty(value = "long_field", required = true)
		long longField,
		@JsonProperty(value = "float_field", required = true)
		float floatField,
		@JsonProperty(value = "double_field", required = true)
		double doubleField,
		@JsonProperty(value = "decimal_field", required = true)
		BigDecimal decimalField,
		@JsonProperty(value = "bool_field", required = true)
		boolean boolField,
		@JsonProperty(value = "string_field", required = true)
		String stringField,
		@JsonProperty(value = "uuid_field", required = true)
		UUID uuidField,
		@JsonProperty(value = "date_field", required = true)
		LocalDate dateField,
		@JsonProperty(value = "datetime_field", required = true)
		LocalDateTime datetimeField,
		@JsonProperty(value = "enum_field", required = true)
		Choice enumField
	) {
		this.intField = intField;
		this.longField = longField;
		this.floatField = floatField;
		this.doubleField = doubleField;
		if (decimalField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.decimalField = decimalField;
		this.boolField = boolField;
		if (stringField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringField = stringField;
		if (uuidField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.uuidField = uuidField;
		if (dateField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateField = dateField;
		if (datetimeField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.datetimeField = datetimeField;
		if (enumField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.enumField = enumField;
	}

	@JsonProperty(value = "int_field", required = true)
	private int intField;

	@JsonProperty(value = "long_field", required = true)
	private long longField;

	@JsonProperty(value = "float_field", required = true)
	private float floatField;

	@JsonProperty(value = "double_field", required = true)
	private double doubleField;

	@JsonProperty(value = "decimal_field", required = true)
	private BigDecimal decimalField;

	@JsonProperty(value = "bool_field", required = true)
	private boolean boolField;

	@JsonProperty(value = "string_field", required = true)
	private String stringField;

	@JsonProperty(value = "uuid_field", required = true)
	private UUID uuidField;

	@JsonProperty(value = "date_field", required = true)
	private LocalDate dateField;

	@JsonProperty(value = "datetime_field", required = true)
	private LocalDateTime datetimeField;

	@JsonProperty(value = "enum_field", required = true)
	private Choice enumField;

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public long getLongField() {
		return longField;
	}

	public void setLongField(long longField) {
		this.longField = longField;
	}

	public float getFloatField() {
		return floatField;
	}

	public void setFloatField(float floatField) {
		this.floatField = floatField;
	}

	public double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}

	public BigDecimal getDecimalField() {
		return decimalField;
	}

	public void setDecimalField(BigDecimal decimalField) {
		if (decimalField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.decimalField = decimalField;
	}

	public boolean getBoolField() {
		return boolField;
	}

	public void setBoolField(boolean boolField) {
		this.boolField = boolField;
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

	public Choice getEnumField() {
		return enumField;
	}

	public void setEnumField(Choice enumField) {
		if (enumField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.enumField = enumField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UrlParameters)) return false;
		UrlParameters that = (UrlParameters) o;
		return getIntField() == that.getIntField() && getLongField() == that.getLongField() && Float.compare(that.getFloatField(), getFloatField()) == 0 && Double.compare(that.getDoubleField(), getDoubleField()) == 0 && Objects.equals(getDecimalField(), that.getDecimalField()) && getBoolField() == that.getBoolField() && Objects.equals(getStringField(), that.getStringField()) && Objects.equals(getUuidField(), that.getUuidField()) && Objects.equals(getDateField(), that.getDateField()) && Objects.equals(getDatetimeField(), that.getDatetimeField()) && Objects.equals(getEnumField(), that.getEnumField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIntField(), getLongField(), getFloatField(), getDoubleField(), getDecimalField(), getBoolField(), getStringField(), getUuidField(), getDateField(), getDatetimeField(), getEnumField());
	}

	@Override
	public String toString() {
		return String.format("UrlParameters{intField=%s, longField=%s, floatField=%s, doubleField=%s, decimalField=%s, boolField=%s, stringField=%s, uuidField=%s, dateField=%s, datetimeField=%s, enumField=%s}", intField, longField, floatField, doubleField, decimalField, boolField, stringField, uuidField, dateField, datetimeField, enumField);
	}
}
