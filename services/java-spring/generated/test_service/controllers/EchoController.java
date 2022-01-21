package test_service.controllers;

import java.math.BigDecimal;
import java.io.IOException;
import java.time.*;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;

import test_service.json.Json;
import test_service.models.*;
import test_service.services.echo.*;

@RestController("EchoController")
public class EchoController {
	private static final Logger logger = LogManager.getLogger(EchoController.class);

	@Autowired
	private EchoService echoService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/echo/body_string")
	public ResponseEntity<String> echoBodyStringController(@RequestBody String bodyStr) throws IOException {
		logger.info("Received request, operationId: echo.echo_body_string, method: POST, url: /echo/body_string");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "text/plain");

		var result = echoService.echoBodyString(bodyStr);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}

	@PostMapping("/echo/body")
	public ResponseEntity<String> echoBodyController(@RequestBody String bodyStr) throws IOException {
		logger.info("Received request, operationId: echo.echo_body, method: POST, url: /echo/body");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		Message requestBody;
		try {
			requestBody = objectMapper.readValue(bodyStr, Message.class);
		} catch (Exception e) {
			logger.error("Completed request with status code: {}", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		var result = echoService.echoBody(requestBody);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/query")
	public ResponseEntity<String> echoQueryController(@RequestParam(name = "int_query") int intQuery, @RequestParam(name = "long_query") long longQuery, @RequestParam(name = "float_query") float floatQuery, @RequestParam(name = "double_query") double doubleQuery, @RequestParam(name = "decimal_query") BigDecimal decimalQuery, @RequestParam(name = "bool_query") boolean boolQuery, @RequestParam(name = "string_query") String stringQuery, @RequestParam(name = "string_opt_query", required = false) String stringOptQuery, @RequestParam(name = "string_defaulted_query", defaultValue = "the default value") String stringDefaultedQuery, @RequestParam(name = "string_array_query") String[] stringArrayQuery, @RequestParam(name = "uuid_query") UUID uuidQuery, @RequestParam(name = "date_query") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateQuery, @RequestParam(name = "date_array_query") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate[] dateArrayQuery, @RequestParam(name = "datetime_query") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetimeQuery, @RequestParam(name = "enum_query") Choice enumQuery) throws IOException {
		logger.info("Received request, operationId: echo.echo_query, method: GET, url: /echo/query");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.echoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/header")
	public ResponseEntity<String> echoHeaderController(@RequestHeader(name = "Int-Header") int intHeader, @RequestHeader(name = "Long-Header") long longHeader, @RequestHeader(name = "Float-Header") float floatHeader, @RequestHeader(name = "Double-Header") double doubleHeader, @RequestHeader(name = "Decimal-Header") BigDecimal decimalHeader, @RequestHeader(name = "Bool-Header") boolean boolHeader, @RequestHeader(name = "String-Header") String stringHeader, @RequestHeader(name = "String-Opt-Header", required = false) String stringOptHeader, @RequestHeader(name = "String-Defaulted-Header", defaultValue = "the default value") String stringDefaultedHeader, @RequestHeader(name = "String-Array-Header") String[] stringArrayHeader, @RequestHeader(name = "Uuid-Header") UUID uuidHeader, @RequestHeader(name = "Date-Header") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateHeader, @RequestHeader(name = "Date-Array-Header") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate[] dateArrayHeader, @RequestHeader(name = "Datetime-Header") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetimeHeader, @RequestHeader(name = "Enum-Header") Choice enumHeader) throws IOException {
		logger.info("Received request, operationId: echo.echo_header, method: GET, url: /echo/header");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.echoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/url_params/{int_url}/{long_url}/{float_url}/{double_url}/{decimal_url}/{bool_url}/{string_url}/{uuid_url}/{date_url}/{datetime_url}/{enum_url}")
	public ResponseEntity<String> echoUrlParamsController(@PathVariable(name = "int_url") int intUrl, @PathVariable(name = "long_url") long longUrl, @PathVariable(name = "float_url") float floatUrl, @PathVariable(name = "double_url") double doubleUrl, @PathVariable(name = "decimal_url") BigDecimal decimalUrl, @PathVariable(name = "bool_url") boolean boolUrl, @PathVariable(name = "string_url") String stringUrl, @PathVariable(name = "uuid_url") UUID uuidUrl, @PathVariable(name = "date_url") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateUrl, @PathVariable(name = "datetime_url") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetimeUrl, @PathVariable(name = "enum_url") Choice enumUrl) throws IOException {
		logger.info("Received request, operationId: echo.echo_url_params, method: GET, url: /echo/url_params/{int_url}/{long_url}/{float_url}/{double_url}/{decimal_url}/{bool_url}/{string_url}/{uuid_url}/{date_url}/{datetime_url}/{enum_url}");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.echoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@PostMapping("/echo/everything/{date_url}/{decimal_url}")
	public ResponseEntity<String> echoEverythingController(@RequestBody String bodyStr, @RequestParam(name = "float_query") float floatQuery, @RequestParam(name = "bool_query") boolean boolQuery, @RequestHeader(name = "Uuid-Header") UUID uuidHeader, @RequestHeader(name = "Datetime-Header") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetimeHeader, @PathVariable(name = "date_url") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateUrl, @PathVariable(name = "decimal_url") BigDecimal decimalUrl) throws IOException {
		logger.info("Received request, operationId: echo.echo_everything, method: POST, url: /echo/everything/{date_url}/{decimal_url}");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		Message requestBody;
		try {
			requestBody = objectMapper.readValue(bodyStr, Message.class);
		} catch (Exception e) {
			logger.error("Completed request with status code: {}", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		var result = echoService.echoEverything(requestBody, floatQuery, boolQuery, uuidHeader, datetimeHeader, dateUrl, decimalUrl);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (result instanceof EchoEverythingResponse.Ok) {
			String responseJson = objectMapper.writeValueAsString(((EchoEverythingResponse.Ok) result).body);
			logger.info("Completed request with status code: {}", HttpStatus.OK);
			return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
		}

		if (result instanceof EchoEverythingResponse.Forbidden) {
			logger.info("Completed request with status code: {}", HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/echo/same_operation_name")
	public ResponseEntity<String> sameOperationNameController() throws IOException {
		logger.info("Received request, operationId: echo.same_operation_name, method: GET, url: /echo/same_operation_name");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.sameOperationName();
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (result instanceof SameOperationNameResponse.Ok) {
			logger.info("Completed request with status code: {}", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		if (result instanceof SameOperationNameResponse.Forbidden) {
			logger.info("Completed request with status code: {}", HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
