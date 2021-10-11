package test_service.models;

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

	public void setFieldInt(int value) {
		this.fieldInt = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MessageCamelCase)) return false;
		MessageCamelCase that = (MessageCamelCase) o;
		return getFieldInt() == that.getFieldInt();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFieldInt());
	}

	@Override
	public String toString() {
		return String.format("MessageCamelCase{fieldInt=%s}", fieldInt);
	}
}
