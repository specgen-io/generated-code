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
		this.data = data;
	}
}
