package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public enum Choice {
	@JsonProperty("One") FIRST_CHOICE,
	@JsonProperty("Two") SECOND_CHOICE,
	@JsonProperty("Three") THIRD_CHOICE,
}
