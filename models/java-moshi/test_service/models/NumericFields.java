package test_service.models;

import com.squareup.moshi.Json;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class NumericFields {

	@Json(name = "int_field")
	private int intField;

	@Json(name = "long_field")
	private long longField;

	@Json(name = "float_field")
	private float floatField;

	@Json(name = "double_field")
	private double doubleField;

	@Json(name = "decimal_field")
	private BigDecimal decimalField;

	public NumericFields(int intField, long longField, float floatField, double doubleField, BigDecimal decimalField) {
		this.intField = intField;
		this.longField = longField;
		this.floatField = floatField;
		this.doubleField = doubleField;
		this.decimalField = decimalField;
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
