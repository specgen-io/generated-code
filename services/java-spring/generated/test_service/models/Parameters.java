package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class Parameters {

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

	@JsonProperty(value = "string_opt_field", required = false)
	private String stringOptField;

	@JsonProperty(value = "string_defaulted_field", required = true)
	private String stringDefaultedField;

	@JsonProperty(value = "string_array_field", required = true)
	private String[] stringArrayField;

	@JsonProperty(value = "uuid_field", required = true)
	private UUID uuidField;

	@JsonProperty(value = "date_field", required = true)
	private LocalDate dateField;

	@JsonProperty(value = "date_array_field", required = true)
	private LocalDate[] dateArrayField;

	@JsonProperty(value = "datetime_field", required = true)
	private LocalDateTime datetimeField;

	@JsonProperty(value = "enum_field", required = true)
	private Choice enumField;

	@JsonCreator
	public Parameters(
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
		@JsonProperty(value = "string_opt_field", required = false)
		String stringOptField,
		@JsonProperty(value = "string_defaulted_field", required = true)
		String stringDefaultedField,
		@JsonProperty(value = "string_array_field", required = true)
		String[] stringArrayField,
		@JsonProperty(value = "uuid_field", required = true)
		UUID uuidField,
		@JsonProperty(value = "date_field", required = true)
		LocalDate dateField,
		@JsonProperty(value = "date_array_field", required = true)
		LocalDate[] dateArrayField,
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
		this.stringOptField = stringOptField;
		if (stringDefaultedField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringDefaultedField = stringDefaultedField;
		if (stringArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.stringArrayField = stringArrayField;
		if (uuidField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.uuidField = uuidField;
		if (dateField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateField = dateField;
		if (dateArrayField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateArrayField = dateArrayField;
		if (datetimeField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.datetimeField = datetimeField;
		if (enumField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.enumField = enumField;
	}

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
		this.stringField = stringField;
	}

	public String getStringOptField() {
		return stringOptField;
	}

	public void setStringOptField(String stringOptField) {
		this.stringOptField = stringOptField;
	}

	public String getStringDefaultedField() {
		return stringDefaultedField;
	}

	public void setStringDefaultedField(String stringDefaultedField) {
		this.stringDefaultedField = stringDefaultedField;
	}

	public String[] getStringArrayField() {
		return stringArrayField;
	}

	public void setStringArrayField(String[] stringArrayField) {
		this.stringArrayField = stringArrayField;
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

	public LocalDate[] getDateArrayField() {
		return dateArrayField;
	}

	public void setDateArrayField(LocalDate[] dateArrayField) {
		this.dateArrayField = dateArrayField;
	}

	public LocalDateTime getDatetimeField() {
		return datetimeField;
	}

	public void setDatetimeField(LocalDateTime datetimeField) {
		this.datetimeField = datetimeField;
	}

	public Choice getEnumField() {
		return enumField;
	}

	public void setEnumField(Choice enumField) {
		this.enumField = enumField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Parameters)) return false;
		Parameters that = (Parameters) o;
		return getIntField() == that.getIntField() && getLongField() == that.getLongField() && Float.compare(that.getFloatField(), getFloatField()) == 0 && Double.compare(that.getDoubleField(), getDoubleField()) == 0 && Objects.equals(getDecimalField(), that.getDecimalField()) && getBoolField() == that.getBoolField() && Objects.equals(getStringField(), that.getStringField()) && Objects.equals(getStringOptField(), that.getStringOptField()) && Objects.equals(getStringDefaultedField(), that.getStringDefaultedField()) && Arrays.equals(getStringArrayField(), that.getStringArrayField()) && Objects.equals(getUuidField(), that.getUuidField()) && Objects.equals(getDateField(), that.getDateField()) && Arrays.equals(getDateArrayField(), that.getDateArrayField()) && Objects.equals(getDatetimeField(), that.getDatetimeField()) && Objects.equals(getEnumField(), that.getEnumField());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getIntField(), getLongField(), getFloatField(), getDoubleField(), getDecimalField(), getBoolField(), getStringField(), getStringOptField(), getStringDefaultedField(), getUuidField(), getDateField(), getDatetimeField(), getEnumField());
		result = 31 * result + Arrays.hashCode(getStringArrayField());
		result = 31 * result + Arrays.hashCode(getDateArrayField());
		return result;
	}

	@Override
	public String toString() {
		return String.format("Parameters{intField=%s, longField=%s, floatField=%s, doubleField=%s, decimalField=%s, boolField=%s, stringField=%s, stringOptField=%s, stringDefaultedField=%s, stringArrayField=%s, uuidField=%s, dateField=%s, dateArrayField=%s, datetimeField=%s, enumField=%s}", intField, longField, floatField, doubleField, decimalField, boolField, stringField, stringOptField, stringDefaultedField, Arrays.toString(stringArrayField), uuidField, dateField, Arrays.toString(dateArrayField), datetimeField, enumField);
	}
}
