package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class EnumFields {
	@JsonProperty("enum_field")
	private Choice enumField;

	public EnumFields() {
	}

	public EnumFields(Choice enumField) {
		this.enumField = enumField;
	}

	public Choice getEnumField() {
		return enumField;
	}

	public void setEnumField(Choice enumField) {
		this.enumField = enumField;
	}
}
