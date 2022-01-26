package test_service.models;

import com.squareup.moshi.Json;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class RawJsonField {

	@Json(name = "json_field")
	private Map<String, Object> jsonField;

	public RawJsonField(Map<String, Object> jsonField) {
		this.jsonField = jsonField;
	}

	public Map<String, Object> getJsonField() {
		return jsonField;
	}

	public void setJsonField(Map<String, Object> jsonField) {
		this.jsonField = jsonField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RawJsonField)) return false;
		RawJsonField that = (RawJsonField) o;
		return Objects.equals(getJsonField(), that.getJsonField());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getJsonField());
	}

	@Override
	public String toString() {
		return String.format("RawJsonField{jsonField=%s}", jsonField);
	}
}
