package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class EnumFields {

	@JsonProperty(value = "enum_field", required = true)
	private Choice enumField;

	@JsonCreator
	public EnumFields(
		@JsonProperty(value = "enum_field", required = true)
		Choice enumField
	) {
		if (enumField == null) { throw new IllegalArgumentException("null value is not allowed"); }
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
