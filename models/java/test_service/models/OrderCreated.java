package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderCreated {
	@JsonProperty("id")
	private UUID id;
	@JsonProperty("sku")
	private String sku;
	@JsonProperty("quantity")
	private int quantity;

	public OrderCreated() {
	}

	public OrderCreated(UUID id, String sku, int quantity) {
		this.id = id;
		this.sku = sku;
		this.quantity = quantity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID value) {
		this.id = value;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String value) {
		this.sku = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int value) {
		this.quantity = value;
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
