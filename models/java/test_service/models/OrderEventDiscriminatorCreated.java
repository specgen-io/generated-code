package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventDiscriminatorCreated implements OrderEventDiscriminator {
	@JsonUnwrapped
	public OrderCreated data;

	public OrderEventDiscriminatorCreated() {
	}

	public OrderEventDiscriminatorCreated(OrderCreated data) {
		if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.data = data;
	}

	public OrderCreated getData() {
		return data;
	}

	public void setData(OrderCreated data) {
		if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderEventDiscriminatorCreated)) return false;
		OrderEventDiscriminatorCreated that = (OrderEventDiscriminatorCreated) o;
		return Objects.equals(getData(), that.getData());
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
