package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
	@Type(value = OrderEventCamelCaseCreatedOrder.class, name = "createdOrder"),
	@Type(value = OrderEventCamelCaseChangedOrder.class, name = "changedOrder"),
	@Type(value = OrderEventCamelCaseCanceledOrder.class, name = "canceledOrder"),
})
public interface OrderEventCamelCase {
}
