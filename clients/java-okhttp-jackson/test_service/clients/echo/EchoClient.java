package test_service.clients.echo;

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
import test_service.models.*;
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

	public String echoBodyString(String body) {
		var requestBody = RequestBody.create(body, MediaType.parse("text/plain"));
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("echo/body_string");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: echo.echo_body_string, method: POST, url: /echo/body_string");
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
				String responseBody;
				try {
					responseBody = response.body().string();
				} catch (IOException e) {
					var errorMessage = "Failed to convert response body to string " + e.getMessage();
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
		url.addPathSegments("echo/body_model");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: echo.echo_body_model, method: POST, url: /echo/body_model");
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

	public List<String> echoBodyArray(List<String> body) {
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
		url.addPathSegments("echo/body_array");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: echo.echo_body_array, method: POST, url: /echo/body_array");
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
				List<String> responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<List<String>>() {});
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

	public Map<String, String> echoBodyMap(Map<String, String> body) {
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
		url.addPathSegments("echo/body_map");

		var request = new RequestBuilder("POST", url.build(), requestBody);

		logger.info("Sending request, operationId: echo.echo_body_map, method: POST, url: /echo/body_map");
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
				Map<String, String> responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<Map<String, String>>() {});
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

	public Parameters echoQuery(int intQuery, long longQuery, float floatQuery, double doubleQuery, BigDecimal decimalQuery, boolean boolQuery, String stringQuery, String stringOptQuery, String stringDefaultedQuery, List<String> stringArrayQuery, UUID uuidQuery, LocalDate dateQuery, List<LocalDate> dateArrayQuery, LocalDateTime datetimeQuery, Choice enumQuery) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("echo/query");
		url.addQueryParameter("int_query", intQuery);
		url.addQueryParameter("long_query", longQuery);
		url.addQueryParameter("float_query", floatQuery);
		url.addQueryParameter("double_query", doubleQuery);
		url.addQueryParameter("decimal_query", decimalQuery);
		url.addQueryParameter("bool_query", boolQuery);
		url.addQueryParameter("string_query", stringQuery);
		url.addQueryParameter("string_opt_query", stringOptQuery);
		url.addQueryParameter("string_defaulted_query", stringDefaultedQuery);
		url.addQueryParameter("string_array_query", stringArrayQuery);
		url.addQueryParameter("uuid_query", uuidQuery);
		url.addQueryParameter("date_query", dateQuery);
		url.addQueryParameter("date_array_query", dateArrayQuery);
		url.addQueryParameter("datetime_query", datetimeQuery);
		url.addQueryParameter("enum_query", enumQuery);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.echo_query, method: GET, url: /echo/query");
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
				Parameters responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<Parameters>() {});
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

	public Parameters echoHeader(int intHeader, long longHeader, float floatHeader, double doubleHeader, BigDecimal decimalHeader, boolean boolHeader, String stringHeader, String stringOptHeader, String stringDefaultedHeader, List<String> stringArrayHeader, UUID uuidHeader, LocalDate dateHeader, List<LocalDate> dateArrayHeader, LocalDateTime datetimeHeader, Choice enumHeader) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("echo/header");

		var request = new RequestBuilder("GET", url.build(), null);
		request.addHeaderParameter("Int-Header", intHeader);
		request.addHeaderParameter("Long-Header", longHeader);
		request.addHeaderParameter("Float-Header", floatHeader);
		request.addHeaderParameter("Double-Header", doubleHeader);
		request.addHeaderParameter("Decimal-Header", decimalHeader);
		request.addHeaderParameter("Bool-Header", boolHeader);
		request.addHeaderParameter("String-Header", stringHeader);
		request.addHeaderParameter("String-Opt-Header", stringOptHeader);
		request.addHeaderParameter("String-Defaulted-Header", stringDefaultedHeader);
		request.addHeaderParameter("String-Array-Header", stringArrayHeader);
		request.addHeaderParameter("Uuid-Header", uuidHeader);
		request.addHeaderParameter("Date-Header", dateHeader);
		request.addHeaderParameter("Date-Array-Header", dateArrayHeader);
		request.addHeaderParameter("Datetime-Header", datetimeHeader);
		request.addHeaderParameter("Enum-Header", enumHeader);

		logger.info("Sending request, operationId: echo.echo_header, method: GET, url: /echo/header");
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
				Parameters responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<Parameters>() {});
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

	public UrlParameters echoUrlParams(int intUrl, long longUrl, float floatUrl, double doubleUrl, BigDecimal decimalUrl, boolean boolUrl, String stringUrl, UUID uuidUrl, LocalDate dateUrl, LocalDateTime datetimeUrl, Choice enumUrl) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("echo/url_params");
		url.addPathParameter(intUrl);
		url.addPathParameter(longUrl);
		url.addPathParameter(floatUrl);
		url.addPathParameter(doubleUrl);
		url.addPathParameter(decimalUrl);
		url.addPathParameter(boolUrl);
		url.addPathParameter(stringUrl);
		url.addPathParameter(uuidUrl);
		url.addPathParameter(dateUrl);
		url.addPathParameter(datetimeUrl);
		url.addPathParameter(enumUrl);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.echo_url_params, method: GET, url: /echo/url_params/{int_url}/{long_url}/{float_url}/{double_url}/{decimal_url}/{bool_url}/{string_url}/{uuid_url}/{date_url}/{datetime_url}/{enum_url}");
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
				UrlParameters responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<UrlParameters>() {});
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

	public EchoEverythingResponse echoEverything(Message body, float floatQuery, boolean boolQuery, UUID uuidHeader, LocalDateTime datetimeHeader, LocalDate dateUrl, BigDecimal decimalUrl) {
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
		url.addPathSegments("echo/everything");
		url.addPathParameter(dateUrl);
		url.addPathParameter(decimalUrl);
		url.addQueryParameter("float_query", floatQuery);
		url.addQueryParameter("bool_query", boolQuery);

		var request = new RequestBuilder("POST", url.build(), requestBody);
		request.addHeaderParameter("Uuid-Header", uuidHeader);
		request.addHeaderParameter("Datetime-Header", datetimeHeader);

		logger.info("Sending request, operationId: echo.echo_everything, method: POST, url: /echo/everything/{date_url}/{decimal_url}");
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
				Everything responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<Everything>() {});
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body " + e.getMessage();
					logger.error(errorMessage);
					throw new ClientException(errorMessage, e);
				}
				return new EchoEverythingResponse.Ok(responseBody);
			}
			case 403: {
				logger.info("Received response with status code {}", response.code());
				return new EchoEverythingResponse.Forbidden();
			}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public SameOperationNameResponse sameOperationName() {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("echo/same_operation_name");

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.same_operation_name, method: GET, url: /echo/same_operation_name");
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
				return new SameOperationNameResponse.Ok();
			}
			case 403: {
				logger.info("Received response with status code {}", response.code());
				return new SameOperationNameResponse.Forbidden();
			}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}

	public EchoSuccessResponse echoSuccess(String resultStatus) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegments("echo/success");
		url.addQueryParameter("result_status", resultStatus);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: echo.echo_success, method: GET, url: /echo/success");
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
				OkResult responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<OkResult>() {});
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body " + e.getMessage();
					logger.error(errorMessage);
					throw new ClientException(errorMessage, e);
				}
				return new EchoSuccessResponse.Ok(responseBody);
			}
			case 201: {
				logger.info("Received response with status code {}", response.code());
				CreatedResult responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<CreatedResult>() {});
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body " + e.getMessage();
					logger.error(errorMessage);
					throw new ClientException(errorMessage, e);
				}
				return new EchoSuccessResponse.Created(responseBody);
			}
			case 202: {
				logger.info("Received response with status code {}", response.code());
				AcceptedResult responseBody;
				try {
					responseBody = objectMapper.readValue(response.body().string(), new TypeReference<AcceptedResult>() {});
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body " + e.getMessage();
					logger.error(errorMessage);
					throw new ClientException(errorMessage, e);
				}
				return new EchoSuccessResponse.Accepted(responseBody);
			}
			default:
				var errorMessage = "Unexpected status code received: " + response.code();
				logger.error(errorMessage);
				throw new ClientException(errorMessage);
		}
	}
}
