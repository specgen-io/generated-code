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
}
