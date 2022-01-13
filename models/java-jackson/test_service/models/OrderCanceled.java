package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderCanceled {

@JsonProperty(value = "id", required = true)
	private UUID id;
	@JsonCreator
	public OrderCanceled(
		@JsonProperty(value = "id", required = true)
		UUID id
	) {
		if (id == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderCanceled)) return false;
		OrderCanceled that = (OrderCanceled) o;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return String.format("OrderCanceled{id=%s}", id);
	}
}
