package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderChanged {
	public OrderChanged() {
	}

	public OrderChanged(UUID id, int quantity) {
		if (id == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.id = id;
		this.quantity = quantity;
	}

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("quantity")
	private int quantity;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		if (id == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderChanged)) return false;
		OrderChanged that = (OrderChanged) o;
		return Objects.equals(getId(), that.getId()) && getQuantity() == that.getQuantity();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getQuantity());
	}

	@Override
	public String toString() {
		return String.format("OrderChanged{id=%s, quantity=%s}", id, quantity);
	}
}
