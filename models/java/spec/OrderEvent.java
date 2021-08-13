package test_service.models.spec;

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
	@Type(value = OrderEventCreated.class, name = "created"),
	@Type(value = OrderEventChanged.class, name = "changed"),
	@Type(value = OrderEventCanceled.class, name = "canceled"),
})
public interface OrderEvent {
}
