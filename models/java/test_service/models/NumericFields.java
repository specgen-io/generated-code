package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class NumericFields {
	public NumericFields() {
	}

	public NumericFields(int intField, long longField, float floatField, double doubleField, BigDecimal decimalField) {
		this.intField = intField;
		this.longField = longField;
		this.floatField = floatField;
		this.doubleField = doubleField;
		if (decimalField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.decimalField = decimalField;
	}

	@JsonProperty("int_field")
	private int intField;

	@JsonProperty("long_field")
	private long longField;

	@JsonProperty("float_field")
	private float floatField;

	@JsonProperty("double_field")
	private double doubleField;

	@JsonProperty("decimal_field")
	private BigDecimal decimalField;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NumericFields)) return false;
		NumericFields that = (NumericFields) o;
		return getIntField() == that.getIntField() && getLongField() == that.getLongField() && Float.compare(that.getFloatField(), getFloatField()) == 0 && Double.compare(that.getDoubleField(), getDoubleField()) == 0 && Objects.equals(getDecimalField(), that.getDecimalField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIntField(), getLongField(), getFloatField(), getDoubleField(), getDecimalField());
	}

	@Override
	public String toString() {
		return String.format("NumericFields{intField=%s, longField=%s, floatField=%s, doubleField=%s, decimalField=%s}", intField, longField, floatField, doubleField, decimalField);
	}
}
