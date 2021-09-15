package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "_type"
)
@JsonSubTypes({
	@Type(value = OrderEventDiscriminatedCreated.class, name = "created"),
	@Type(value = OrderEventDiscriminatedChanged.class, name = "changed"),
	@Type(value = OrderEventDiscriminatedCanceled.class, name = "canceled"),
})
public interface OrderEventDiscriminated {
}
