package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventCamelCaseCreatedOrder implements OrderEventCamelCase {
	@JsonUnwrapped
	public OrderCreated data;

	public OrderEventCamelCaseCreatedOrder() {
	}

	public OrderEventCamelCaseCreatedOrder(OrderCreated data) {
		this.data = data;
	}
}
