package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class MessageCamelCase {
	@JsonProperty("field_int")
	private int fieldInt;

	public MessageCamelCase() {
	}

	public MessageCamelCase(int fieldInt) {
		this.fieldInt = fieldInt;
	}

	public int getFieldInt() {
		return fieldInt;
	}

	public void setFieldInt(int fieldInt) {
		this.fieldInt = fieldInt;
	}
}
