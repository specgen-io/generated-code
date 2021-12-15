package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class Everything {
	@JsonCreator
	public Everything(
		@JsonProperty(value = "body_field", required = true)
		Message bodyField,
		@JsonProperty(value = "float_query", required = true)
		float floatQuery,
		@JsonProperty(value = "bool_query", required = true)
		boolean boolQuery,
		@JsonProperty(value = "uuid_header", required = true)
		UUID uuidHeader,
		@JsonProperty(value = "datetime_header", required = true)
		LocalDateTime datetimeHeader,
		@JsonProperty(value = "date_url", required = true)
		LocalDate dateUrl,
		@JsonProperty(value = "decimal_url", required = true)
		BigDecimal decimalUrl
	) {
		if (bodyField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.bodyField = bodyField;
		this.floatQuery = floatQuery;
		this.boolQuery = boolQuery;
		if (uuidHeader == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.uuidHeader = uuidHeader;
		if (datetimeHeader == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.datetimeHeader = datetimeHeader;
		if (dateUrl == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateUrl = dateUrl;
		if (decimalUrl == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.decimalUrl = decimalUrl;
	}

	@JsonProperty(value = "body_field", required = true)
	private Message bodyField;

	@JsonProperty(value = "float_query", required = true)
	private float floatQuery;

	@JsonProperty(value = "bool_query", required = true)
	private boolean boolQuery;

	@JsonProperty(value = "uuid_header", required = true)
	private UUID uuidHeader;

	@JsonProperty(value = "datetime_header", required = true)
	private LocalDateTime datetimeHeader;

	@JsonProperty(value = "date_url", required = true)
	private LocalDate dateUrl;

	@JsonProperty(value = "decimal_url", required = true)
	private BigDecimal decimalUrl;

	public Message getBodyField() {
		return bodyField;
	}

	public void setBodyField(Message bodyField) {
		if (bodyField == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.bodyField = bodyField;
	}

	public float getFloatQuery() {
		return floatQuery;
	}

	public void setFloatQuery(float floatQuery) {
		this.floatQuery = floatQuery;
	}

	public boolean getBoolQuery() {
		return boolQuery;
	}

	public void setBoolQuery(boolean boolQuery) {
		this.boolQuery = boolQuery;
	}

	public UUID getUuidHeader() {
		return uuidHeader;
	}

	public void setUuidHeader(UUID uuidHeader) {
		if (uuidHeader == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.uuidHeader = uuidHeader;
	}

	public LocalDateTime getDatetimeHeader() {
		return datetimeHeader;
	}

	public void setDatetimeHeader(LocalDateTime datetimeHeader) {
		if (datetimeHeader == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.datetimeHeader = datetimeHeader;
	}

	public LocalDate getDateUrl() {
		return dateUrl;
	}

	public void setDateUrl(LocalDate dateUrl) {
		if (dateUrl == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.dateUrl = dateUrl;
	}

	public BigDecimal getDecimalUrl() {
		return decimalUrl;
	}

	public void setDecimalUrl(BigDecimal decimalUrl) {
		if (decimalUrl == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.decimalUrl = decimalUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Everything)) return false;
		Everything that = (Everything) o;
		return Objects.equals(getBodyField(), that.getBodyField()) && Float.compare(that.getFloatQuery(), getFloatQuery()) == 0 && getBoolQuery() == that.getBoolQuery() && Objects.equals(getUuidHeader(), that.getUuidHeader()) && Objects.equals(getDatetimeHeader(), that.getDatetimeHeader()) && Objects.equals(getDateUrl(), that.getDateUrl()) && Objects.equals(getDecimalUrl(), that.getDecimalUrl());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBodyField(), getFloatQuery(), getBoolQuery(), getUuidHeader(), getDatetimeHeader(), getDateUrl(), getDecimalUrl());
	}

	@Override
	public String toString() {
		return String.format("Everything{bodyField=%s, floatQuery=%s, boolQuery=%s, uuidHeader=%s, datetimeHeader=%s, dateUrl=%s, decimalUrl=%s}", bodyField, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl);
	}
}
