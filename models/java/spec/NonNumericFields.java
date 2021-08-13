package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class NonNumericFields {
	@JsonProperty("boolean_field")
	private boolean booleanField;
	@JsonProperty("string_field")
	private String stringField;
	@JsonProperty("uuid_field")
	private UUID uuidField;
	@JsonProperty("date_field")
	private LocalDate dateField;
	@JsonProperty("datetime_field")
	private LocalDateTime datetimeField;

	public NonNumericFields() {
	}

	public NonNumericFields(boolean booleanField, String stringField, UUID uuidField, LocalDate dateField, LocalDateTime datetimeField) {
		this.booleanField = booleanField;
		this.stringField = stringField;
		this.uuidField = uuidField;
		this.dateField = dateField;
		this.datetimeField = datetimeField;
	}

	public boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(boolean booleanField) {
		this.booleanField = booleanField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public UUID getUuidField() {
		return uuidField;
	}

	public void setUuidField(UUID uuidField) {
		this.uuidField = uuidField;
	}

	public LocalDate getDateField() {
		return dateField;
	}

	public void setDateField(LocalDate dateField) {
		this.dateField = dateField;
	}

	public LocalDateTime getDatetimeField() {
		return datetimeField;
	}

	public void setDatetimeField(LocalDateTime datetimeField) {
		this.datetimeField = datetimeField;
	}
}
