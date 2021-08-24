package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public class OrderEventDiscriminatedChanged implements OrderEventDiscriminated {
	@JsonUnwrapped
	public OrderChanged data;

	public OrderEventDiscriminatedChanged() {
	}

	public OrderEventDiscriminatedChanged(OrderChanged data) {
		this.data = data;
	}
}