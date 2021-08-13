package test_service.models.spec;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventChanged implements OrderEvent {
	@JsonUnwrapped
	public OrderChanged data;

	public OrderEventChanged() {
	}

	public OrderEventChanged(OrderChanged data) {
		this.data = data;
	}
}
