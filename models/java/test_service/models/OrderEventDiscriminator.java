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
	@Type(value = OrderEventDiscriminatorCreated.class, name = "created"),
	@Type(value = OrderEventDiscriminatorChanged.class, name = "changed"),
	@Type(value = OrderEventDiscriminatorCanceled.class, name = "canceled"),
})
public interface OrderEventDiscriminator {
}
