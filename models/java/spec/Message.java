package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Message {
	@JsonProperty("field")
	private int field;

	public Message() {
	}

	public Message(int field) {
		this.field = field;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}
}
