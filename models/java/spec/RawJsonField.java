package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class RawJsonField {
	@JsonProperty("json_field")
	@JsonRawValue
	private Object jsonField;

	public RawJsonField() {
	}

	public RawJsonField(String jsonField) {
		this.jsonField = jsonField;
	}

	public String getJsonField() {
		return  jsonField == null ? null : jsonField.toString();
	}

	public void setJsonField(JsonNode node) {
		this.jsonField = node;
	}
}
