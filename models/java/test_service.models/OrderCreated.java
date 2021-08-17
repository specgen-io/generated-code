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
}
