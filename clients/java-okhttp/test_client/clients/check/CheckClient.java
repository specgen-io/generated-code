package test_client.clients.check;

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
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
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

	public void checkQuery(String pString, String pStringOpt, String[] pStringArray, LocalDate pDate, LocalDate[] pDateArray, LocalDateTime pDatetime, int pInt, long pLong, BigDecimal pDecimal, Choice pEnum, String pStringDefaulted) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("check/query");
		url.addQueryParameter("p_string", pString);
		url.addQueryParameter("p_string_opt", pStringOpt);
		url.addQueryParameter("p_string_array", pStringArray);
		url.addQueryParameter("p_date", pDate);
		url.addQueryParameter("p_date_array", pDateArray);
		url.addQueryParameter("p_datetime", pDatetime);
		url.addQueryParameter("p_int", pInt);
		url.addQueryParameter("p_long", pLong);
		url.addQueryParameter("p_decimal", pDecimal);
		url.addQueryParameter("p_enum", pEnum);
		url.addQueryParameter("p_string_defaulted", pStringDefaulted);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: check.check_query, method: GET, url: /check/query");
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
			return;
		default:
			var errorMessage = "Unexpected status code received: " + response.code();
			logger.error(errorMessage);
			throw new ClientException(errorMessage);
		}
	}

	public void checkUrlParams(long intUrl, String stringUrl, float floatUrl, boolean boolUrl, UUID uuidUrl, BigDecimal decimalUrl, LocalDate dateUrl, Choice enumUrl) {
		var url = new UrlBuilder(baseUrl);
		url.addPathSegment("check/url_params");
		url.addPathSegment(intUrl);
		url.addPathSegment(stringUrl);
		url.addPathSegment(floatUrl);
		url.addPathSegment(boolUrl);
		url.addPathSegment(uuidUrl);
		url.addPathSegment(decimalUrl);
		url.addPathSegment(dateUrl);
		url.addPathSegment(enumUrl);

		var request = new RequestBuilder("GET", url.build(), null);

		logger.info("Sending request, operationId: check.check_url_params, method: GET, url: /check/url_params/{int_url}/{string_url}/{float_url}/{bool_url}/{uuid_url}/{decimal_url}/{date_url}/{enum_url}");
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
			var errorMessage = "Failed to execute the request ";
			logger.error(errorMessage);
			throw new ClientException(errorMessage + e.getMessage(), e);
		}

		switch (response.code()) {
			case 200:
				try {
					logger.info("Received response with status code {}", response.code());
					return new CheckForbiddenResponseOk(objectMapper.readValue(response.body().string(), Message.class));
				} catch (IOException e) {
					var errorMessage = "Failed to deserialize response body ";
					logger.error(errorMessage);
					throw new ClientException(errorMessage + e.getMessage(), e);
				}
				case 403:
				logger.info("Received response with status code {}", response.code());
				return new CheckForbiddenResponseForbidden();
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
