package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;

import java.io.*;

public class Json {
	public static void setupObjectMapper(ObjectMapper objectMapper) {
		objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
}