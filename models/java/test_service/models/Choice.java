package test_service.models;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;

public enum Choice {
	@JsonProperty("One") FIRST_CHOICE,
	@JsonProperty("Two") SECOND_CHOICE,
	@JsonProperty("Three") THIRD_CHOICE,
}
