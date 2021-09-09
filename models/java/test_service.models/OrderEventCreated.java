package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventCreated implements OrderEvent {
	@JsonUnwrapped
	public OrderCreated data;

	public OrderEventCreated() {
	}

	public OrderEventCreated(OrderCreated data) {
		this.data = data;
	}

	public OrderCreated getData() {
		return data;
	}

	public void setData(OrderCreated data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderEventCreated)) return false;
		OrderEventCreated created = (OrderEventCreated) o;
		return Objects.equals(getData(), created.getData());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getData());
	}

	@Override
	public String toString() {
		return String.format("OrderCreated{data=%s}", data);
	}
}
