package test_service.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class OrderCreated {

	@Json(name = "id")
	private UUID id;

	@Json(name = "sku")
	private String sku;

	@Json(name = "quantity")
	private int quantity;

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
