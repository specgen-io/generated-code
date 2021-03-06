package test_service.v2.clients.echo;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;
import okhttp3.*;
import org.slf4j.*;
import test_service.*;
import test_service.utils.*;
import test_service.v2.models.*;
import static test_service.json.Json.setupObjectMapper;

public class EchoClient {
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);

	private String baseUrl;
	private ObjectMapper objectMapper;

	private String writeJson(Object result) {
		try {
			return objectMapper.writeValueAsString(result);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	private OkHttpClient client;

	public EchoClient(String baseUrl) {
		this.baseUrl = baseUrl;
		this.objectMapper = new ObjectMapper();
		setupObjectMapper(objectMapper);
		this.client = new OkHttpClient();
	}

	public Message echoBodyModel(Message body) {
		String bodyJson;
		try {
			bodyJson = objectMapper.writeValueAsString(body);
		} catch (Exception e) {
			var errorMessage = "Failed to serialize JSON " + e.getMessage();
			logger.error(errorMessage);
			throw new ClientException(errorMessage, e);
		}

		var requestBody = RequestBody.create(bodyJson, MediaType.parse("application/json"));
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("v2");
		url.addPathSegments("echo/body_model");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: echo.echo_body_model, method: POST, url: /v2/echo/body_model");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request " + e.getMessage();
			logger.error(errorMessage);
			throw new ClientException(errorMessage, e);
		}

		switch (response.code()) {
			case 200: {
				logger.info("Received response with status code {}", response.code());
				Message responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<Message>() {});
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body " + e.getMessage();
					logger.error(errorMessage);
					throw new ClientException(errorMessage, e);
				}
				return responseBody;
			}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}
}
