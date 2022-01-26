package test_service.models;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;


public class EnumFields {

	@Json(name = "enum_field")
	private Choice enumField;

	public EnumFields(Choice enumField) {
		this.enumField = enumField;
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
		if (!(o instanceof EnumFields)) return false;
		EnumFields that = (EnumFields) o;
		return Objects.equals(getEnumField(), that.getEnumField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEnumField());
	}

	@Override
	public String toString() {
		return String.format("EnumFields{enumField=%s}", enumField);
	}
}
