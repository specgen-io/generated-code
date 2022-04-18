package test_service.clients.check;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;
import okhttp3.*;
import org.slf4j.*;
import test_service.*;
import test_service.utils.*;
import test_service.models.*;
import static test_service.json.Json.setupMoshiAdapters;

public class CheckClient {
	private static final Logger logger = LoggerFactory.getLogger(CheckClient.class);

	private String baseUrl;
	private Moshi moshi;
	private OkHttpClient client;

	public CheckClient(String baseUrl) {
		this.baseUrl = baseUrl;
		Moshi.Builder moshiBuilder = new Moshi.Builder();
		setupMoshiAdapters(moshiBuilder);
		this.moshi = moshiBuilder.build();
		this.client = new OkHttpClient();
	}

	public void checkEmpty() {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("check/empty");

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

	public void checkEmptyResponse(Message body) {
		String bodyJson;
		try {
			bodyJson = moshi.adapter(Message.class).toJson(body);
		} catch (AssertionError e) {
			var errorMessage = "Failed to serialize JSON " + e.getMessage();
			logger.error(errorMessage);
			throw new ClientException(errorMessage, e);
		}

		var requestBody = RequestBody.create(bodyJson, MediaType.parse("application/json"));
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("check/empty_response");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: check.check_empty_response, method: POST, url: /check/empty_response");
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
		url.addPathSegments("check/forbidden");

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
					responseBody = moshi.adapter(Message.class).fromJson(response.body().string());
				} catch (Exception e) {
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
		url.addPathSegments("check/same_operation_name");

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
