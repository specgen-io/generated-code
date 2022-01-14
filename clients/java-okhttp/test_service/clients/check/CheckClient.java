package test_service.clients.check;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_service.*;
import test_service.utils.*;
import test_service.models.*;
import test_service.json.Json;

public class CheckClient {
	private static final Logger logger = LoggerFactory.getLogger(CheckClient.class);

	private final String baseUrl;
	private final ObjectMapper objectMapper;
	private final OkHttpClient client;

	public CheckClient(String baseUrl) {
		this.baseUrl = baseUrl;
		this.objectMapper = new ObjectMapper();
		Json.setupObjectMapper(objectMapper);
		this.client = new OkHttpClient();
	}

	public void checkEmpty() {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("check/empty");

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: check.check_empty, method: GET, url: /check/empty");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request " + e.getMessage();
			logger.error(errorMessage);
			throw new ClientException(errorMessage, e);
		}

		switch (response.code()) {
			case 200:
				logger.info("Received response with status code {}", response.code());
				return;
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public CheckForbiddenResponse checkForbidden() {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("check/forbidden");

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: check.check_forbidden, method: GET, url: /check/forbidden");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request " + e.getMessage();
			logger.error(errorMessage);
			throw new ClientException(errorMessage, e);
		}

		switch (response.code()) {
			case 200:
				logger.info("Received response with status code {}", response.code());
				Message responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), Message.class);
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body " + e.getMessage();
					logger.error(errorMessage);
					throw new ClientException(errorMessage, e);
				}
				return new CheckForbiddenResponse.Ok(responseBody);
			case 403:
				logger.info("Received response with status code {}", response.code());
				return new CheckForbiddenResponse.Forbidden();
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public SameOperationNameResponse sameOperationName() {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("check/same_operation_name");

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: check.same_operation_name, method: GET, url: /check/same_operation_name");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request " + e.getMessage();
			logger.error(errorMessage);
			throw new ClientException(errorMessage, e);
		}

		switch (response.code()) {
			case 200:
				logger.info("Received response with status code {}", response.code());
				return new SameOperationNameResponse.Ok();
			case 403:
				logger.info("Received response with status code {}", response.code());
				return new SameOperationNameResponse.Forbidden();
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}
}