package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OptionalFields {
	@JsonProperty("int_option_field")
	private Integer intOptionField;
	@JsonProperty("string_option_field")
	private String stringOptionField;

	public OptionalFields() {
	}

	public OptionalFields(Integer intOptionField, String stringOptionField) {
		this.intOptionField = intOptionField;
		this.stringOptionField = stringOptionField;
	}

	public Integer getIntOptionField() {
		return intOptionField;
	}

	public void setIntOptionField(Integer intOptionField) {
		this.intOptionField = intOptionField;
	}

	public String getStringOptionField() {
		return stringOptionField;
	}

	public void setStringOptionField(String stringOptionField) {
		this.stringOptionField = stringOptionField;
	}
}
