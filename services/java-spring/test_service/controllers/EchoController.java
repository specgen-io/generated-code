package test_service.controllers;

import java.math.BigDecimal;
import java.io.IOException;
import java.time.*;
import java.util.UUID;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;

import test_service.models.Json;
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
	public ResponseEntity<String> echoQueryController(@RequestParam(name = "int_query") int intQuery, @RequestParam(name = "string_query") String stringQuery) throws IOException {
		logger.info("Received request, operationId: echo.echo_query, method: GET, url: /echo/query");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.echoQuery(intQuery, stringQuery);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/header")
	public ResponseEntity<String> echoHeaderController(@RequestHeader(name = "Int-Header") int intHeader, @RequestHeader(name = "String-Header") String stringHeader) throws IOException {
		logger.info("Received request, operationId: echo.echo_header, method: GET, url: /echo/header");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.echoHeader(intHeader, stringHeader);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/url_params/{int_url}/{string_url}")
	public ResponseEntity<String> echoUrlParamsController(@PathVariable(name = "int_url") int intUrl, @PathVariable(name = "string_url") String stringUrl) throws IOException {
		logger.info("Received request, operationId: echo.echo_url_params, method: GET, url: /echo/url_params/{int_url}/{string_url}");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = echoService.echoUrlParams(intUrl, stringUrl);
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
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
