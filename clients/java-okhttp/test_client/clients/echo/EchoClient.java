package test_client.clients.echo;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import org.slf4j.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_client.*;
import test_client.utils.*;
import test_client.models.*;
import test_client.models.Json;

public class EchoClient {
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);

	private final String baseUrl;
	private final ObjectMapper objectMapper;
	private final OkHttpClient client;

	public EchoClient(String baseUrl) {
		this.baseUrl = baseUrl;
		this.objectMapper = new ObjectMapper();
		Json.setupObjectMapper(objectMapper);
		this.client = new OkHttpClient();
	}

	public Message echoBody(Message body) {
		String bodyJson;
		try {
			bodyJson = objectMapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			var errorMessage = "Failed to serialize JSON ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		var requestBody = RequestBody.create(bodyJson, MediaType.parse("application/json"));
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("echo/body");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: echo.echo_body, method: POST, url: /echo/body");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		switch (response.code()) {
			case 200:
				try {
					logger.info("Received response with status code {}", response.code());
					return objectMapper.readValue(response.body().string(), Message.class);
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body ";
					logger.error(errorMessage);
					throw new ClientException(errorMessage + e.getMessage(), e);
				}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public Message echoQuery(int intQuery, String stringQuery) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("echo/query");
		url.addQueryParameter("int_query", intQuery);
		url.addQueryParameter("string_query", stringQuery);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.echo_query, method: GET, url: /echo/query");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		switch (response.code()) {
			case 200:
				try {
					logger.info("Received response with status code {}", response.code());
					return objectMapper.readValue(response.body().string(), Message.class);
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body ";
					logger.error(errorMessage);
					throw new ClientException(errorMessage + e.getMessage(), e);
				}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public Message echoHeader(int intHeader, String stringHeader) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("echo/header");

		var request = new RequestBuilder("GET", url.build(), null);
		request.addHeaderParameter("Int-Header", intHeader);
		request.addHeaderParameter("String-Header", stringHeader);

		logger.info("Sending request, operationId: echo.echo_header, method: GET, url: /echo/header");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		switch (response.code()) {
			case 200:
				try {
					logger.info("Received response with status code {}", response.code());
					return objectMapper.readValue(response.body().string(), Message.class);
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body ";
					logger.error(errorMessage);
					throw new ClientException(errorMessage + e.getMessage(), e);
				}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public Message echoUrlParams(int intUrl, String stringUrl) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("echo/url_params");
		url.addPathSegment(intUrl);
		url.addPathSegment(stringUrl);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.echo_url_params, method: GET, url: /echo/url_params/{int_url}/{string_url}");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		switch (response.code()) {
			case 200:
				try {
					logger.info("Received response with status code {}", response.code());
					return objectMapper.readValue(response.body().string(), Message.class);
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body ";
					logger.error(errorMessage);
					throw new ClientException(errorMessage + e.getMessage(), e);
				}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public SameOperationNameResponse sameOperationName() {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("echo/same_operation_name");

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.same_operation_name, method: GET, url: /echo/same_operation_name");
		Response response;
		try {
			response = client.newCall(request.build()).execute();
		} catch (IOException e) {
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		switch (response.code()) {
			case 200:
			logger.info("Received response with status code {}", response.code());
			return new SameOperationNameResponseOk();
			case 403:
			logger.info("Received response with status code {}", response.code());
			return new SameOperationNameResponseForbidden();
		default:
			var errorMessage = "Unexpected status code received: " + response.code();
			logger.error(errorMessage);
			throw new ClientException(errorMessage);
	}
}
}
