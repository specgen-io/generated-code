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

import test_service.json.Json;
import test_service.models.*;
import test_service.services.check.*;

@RestController("CheckController")
public class CheckController {
	private static final Logger logger = LogManager.getLogger(CheckController.class);

	@Autowired
	private CheckService checkService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/check/empty")
	public ResponseEntity<String> checkEmptyController() throws IOException {
		logger.info("Received request, operationId: check.check_empty, method: GET, url: /check/empty");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		checkService.checkEmpty();

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/check/empty_response")
	public ResponseEntity<String> checkEmptyResponseController(@RequestBody String bodyStr) throws IOException {
		logger.info("Received request, operationId: check.check_empty_response, method: POST, url: /check/empty_response");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		Message requestBody;
		try {
			requestBody = objectMapper.readValue(bodyStr, Message.class);
		} catch (Exception e) {
			logger.error("Completed request with status code: {}", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		checkService.checkEmptyResponse(requestBody);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check/forbidden")
	public ResponseEntity<String> checkForbiddenController() throws IOException {
		logger.info("Received request, operationId: check.check_forbidden, method: GET, url: /check/forbidden");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = checkService.checkForbidden();
		if (result == null) {
			logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (result instanceof CheckForbiddenResponse.Ok) {
			String responseJson = objectMapper.writeValueAsString(((CheckForbiddenResponse.Ok) result).body);
			logger.info("Completed request with status code: {}", HttpStatus.OK);
			return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
		}

		if (result instanceof CheckForbiddenResponse.Forbidden) {
			logger.info("Completed request with status code: {}", HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/check/same_operation_name")
	public ResponseEntity<String> sameOperationNameController() throws IOException {
		logger.info("Received request, operationId: check.same_operation_name, method: GET, url: /check/same_operation_name");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		var result = checkService.sameOperationName();
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
