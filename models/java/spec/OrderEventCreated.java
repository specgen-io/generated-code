package test_service.models.spec;

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
}
