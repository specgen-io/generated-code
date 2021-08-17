package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventDiscriminatedCreated implements OrderEventDiscriminated {
	@JsonUnwrapped
	public OrderCreated data;

	public OrderEventDiscriminatedCreated() {
	}

	public OrderEventDiscriminatedCreated(OrderCreated data) {
		this.data = data;
	}
}
