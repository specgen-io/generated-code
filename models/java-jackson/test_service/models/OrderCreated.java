package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderCreated {

@JsonProperty(value = "id", required = true)
	private UUID id;

@JsonProperty(value = "sku", required = true)
	private String sku;

@JsonProperty(value = "quantity", required = true)
	private int quantity;
	@JsonCreator
	public OrderCreated(
		@JsonProperty(value = "id", required = true)
		UUID id,
		@JsonProperty(value = "sku", required = true)
		String sku,
		@JsonProperty(value = "quantity", required = true)
		int quantity
	) {
		if (id == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.id = id;
		if (sku == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.sku = sku;
		this.quantity = quantity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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
		if (!(o instanceof OrderCreated)) return false;
		OrderCreated that = (OrderCreated) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getSku(), that.getSku()) && getQuantity() == that.getQuantity();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getSku(), getQuantity());
	}

	@Override
	public String toString() {
		return String.format("OrderCreated{id=%s, sku=%s, quantity=%s}", id, sku, quantity);
	}
}
