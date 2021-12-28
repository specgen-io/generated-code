package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public enum Choice {
	@JsonProperty("FIRST_CHOICE") FIRST_CHOICE,
	@JsonProperty("SECOND_CHOICE") SECOND_CHOICE,
	@JsonProperty("THIRD_CHOICE") THIRD_CHOICE,
}
