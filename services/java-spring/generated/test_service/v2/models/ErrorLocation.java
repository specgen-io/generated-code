package test_service.v2.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public enum ErrorLocation {
	@JsonProperty("query") QUERY,
	@JsonProperty("header") HEADER,
	@JsonProperty("body") BODY,
	@JsonProperty("unknown") UNKNOWN,
}
