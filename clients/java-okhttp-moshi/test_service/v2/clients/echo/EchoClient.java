package test_service.v2.clients.echo;

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
import test_service.v2.models.*;
import static test_service.json.Json.setupMoshiAdapters;

public class EchoClient {
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);

	private String baseUrl;
	private Moshi moshi;
	private OkHttpClient client;

	public EchoClient(String baseUrl) {
		this.baseUrl = baseUrl;
		Moshi.Builder moshiBuilder = new Moshi.Builder();
		setupMoshiAdapters(moshiBuilder);
		this.moshi = moshiBuilder.build();
		this.client = new OkHttpClient();
	}

	public Message echoBodyModel(Message body) {
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
				return responseBody;
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}
}
