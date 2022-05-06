package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class OkResult {

	@JsonProperty(value = "ok_result", required = true)
	private String okResult;

	@JsonCreator
	public OkResult(
		@JsonProperty(value = "ok_result", required = true)
		String okResult
	) {
		if (okResult == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.okResult = okResult;
	}

	public String getOkResult() {
		return okResult;
	}

	public void setOkResult(String okResult) {
		this.okResult = okResult;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OkResult)) return false;
		OkResult that = (OkResult) o;
		return Objects.equals(getOkResult(), that.getOkResult());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getOkResult());
	}

	@Override
	public String toString() {
		return String.format("OkResult{okResult=%s}", okResult);
	}
}
