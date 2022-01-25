package test_service.models;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class OrderChanged {

	@Json(name = "id")
	private UUID id;

	@Json(name = "quantity")
	private int quantity;

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
