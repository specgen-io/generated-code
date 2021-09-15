package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;

import java.io.*;

public class Jsoner {

	public static <T> String serialize(ObjectMapper objectMapper, T data) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		objectMapper.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.writeValue(byteArrayOutputStream, data);

		return byteArrayOutputStream.toString();
	}

	public static <T> T deserialize(ObjectMapper objectMapper, String jsonStr, Class<T> tClass) throws IOException {
		InputStream inputStream = new ByteArrayInputStream(jsonStr.getBytes());

		return objectMapper.readValue(inputStream, tClass);
	}
}