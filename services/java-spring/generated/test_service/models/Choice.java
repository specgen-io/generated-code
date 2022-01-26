package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public enum Choice {
	@JsonProperty("FIRST_CHOICE") FIRST_CHOICE,
	@JsonProperty("SECOND_CHOICE") SECOND_CHOICE,
	@JsonProperty("THIRD_CHOICE") THIRD_CHOICE,
}
