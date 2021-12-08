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
	@Type(value = OrderEventWrapperCreated.class, name = "created"),
	@Type(value = OrderEventWrapperChanged.class, name = "changed"),
	@Type(value = OrderEventWrapperCanceled.class, name = "canceled"),
})
public interface OrderEventWrapper {
}
