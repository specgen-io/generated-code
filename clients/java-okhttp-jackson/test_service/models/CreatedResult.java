package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class CreatedResult {

	@JsonProperty(value = "created_result", required = true)
	private String createdResult;

	@JsonCreator
	public CreatedResult(
		@JsonProperty(value = "created_result", required = true)
		String createdResult
	) {
		if (createdResult == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.createdResult = createdResult;
	}

	public String getCreatedResult() {
		return createdResult;
	}

	public void setCreatedResult(String createdResult) {
		this.createdResult = createdResult;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CreatedResult)) return false;
		CreatedResult that = (CreatedResult) o;
		return Objects.equals(getCreatedResult(), that.getCreatedResult());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCreatedResult());
	}

	@Override
	public String toString() {
		return String.format("CreatedResult{createdResult=%s}", createdResult);
	}
}
