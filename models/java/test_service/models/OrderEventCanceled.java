package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventCanceled implements OrderEvent {
	@JsonUnwrapped
	public OrderCanceled data;

	public OrderEventCanceled() {
	}

	public OrderEventCanceled(OrderCanceled data) {
		if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.data = data;
	}

	public OrderCanceled getData() {
		return data;
	}

	public void setData(OrderCanceled data) {
		if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderEventCanceled)) return false;
		OrderEventCanceled that = (OrderEventCanceled) o;
		return Objects.equals(getData(), that.getData());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getData());
	}

	@Override
	public String toString() {
		return String.format("OrderCanceled{data=%s}", data);
	}
}
