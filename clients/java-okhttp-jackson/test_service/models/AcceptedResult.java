package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class AcceptedResult {

	@JsonProperty(value = "accepted_result", required = true)
	private String acceptedResult;

	@JsonCreator
	public AcceptedResult(
		@JsonProperty(value = "accepted_result", required = true)
		String acceptedResult
	) {
		if (acceptedResult == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.acceptedResult = acceptedResult;
	}

	public String getAcceptedResult() {
		return acceptedResult;
	}

	public void setAcceptedResult(String acceptedResult) {
		this.acceptedResult = acceptedResult;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AcceptedResult)) return false;
		AcceptedResult that = (AcceptedResult) o;
		return Objects.equals(getAcceptedResult(), that.getAcceptedResult());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAcceptedResult());
	}

	@Override
	public String toString() {
		return String.format("AcceptedResult{acceptedResult=%s}", acceptedResult);
	}
}
