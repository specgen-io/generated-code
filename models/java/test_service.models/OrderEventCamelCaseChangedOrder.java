package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventCamelCaseChangedOrder implements OrderEventCamelCase {
	@JsonUnwrapped
	public OrderChanged data;

	public OrderEventCamelCaseChangedOrder() {
	}

	public OrderEventCamelCaseChangedOrder(OrderChanged data) {
		this.data = data;
	}

	public OrderChanged getData() {
		return data;
	}

	public void setData(OrderChanged data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderEventCamelCaseChangedOrder)) return false;
		OrderEventCamelCaseChangedOrder changedOrder = (OrderEventCamelCaseChangedOrder) o;
		return Objects.equals(getData(), changedOrder.getData());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getData());
	}

	@Override
	public String toString() {
		return String.format("OrderChanged{data=%s}", data);
	}
}
