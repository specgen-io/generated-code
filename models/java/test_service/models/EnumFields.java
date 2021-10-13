package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class EnumFields {
	public EnumFields() {
	}

	public EnumFields(Choice enumField) {
		if (enumField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.enumField = enumField;
	}

	@JsonProperty("enum_field")
	private Choice enumField;

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
