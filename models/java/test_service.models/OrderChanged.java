package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderChanged {
	@JsonProperty("id")
	private UUID id;
	@JsonProperty("quantity")
	private int quantity;

	public OrderChanged() {
	}

	public OrderChanged(UUID id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
		OrderChanged orderChanged = (OrderChanged) o;
		return Objects.equals(getId(), orderChanged.getId()) && Objects.equals(getQuantity(), orderChanged.getQuantity());
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
