package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventDiscriminatedCanceled implements OrderEventDiscriminated {
	@JsonUnwrapped
	public OrderCanceled data;

	public OrderEventDiscriminatedCanceled() {
	}

	public OrderEventDiscriminatedCanceled(OrderCanceled data) {
		this.data = data;
	}

	public OrderCanceled getData() {
		return data;
	}

	public void setData(OrderCanceled data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderEventDiscriminatedCanceled)) return false;
		OrderEventDiscriminatedCanceled that = (OrderEventDiscriminatedCanceled) o;
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
