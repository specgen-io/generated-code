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
}
