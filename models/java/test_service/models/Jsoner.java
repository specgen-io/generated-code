package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;

import java.io.*;

public class Jsoner {

	public static void setupObjectMapper(ObjectMapper objectMapper) {
		objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	public static <T> String serialize(ObjectMapper objectMapper, T data) throws IOException {
		return objectMapper.writeValueAsString(data);
	}

	public static <T> T deserialize(ObjectMapper objectMapper, String jsonStr, Class<T> tClass) throws IOException {
		return objectMapper.readValue(jsonStr, tClass);
	}
}