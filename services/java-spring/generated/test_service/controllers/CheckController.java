package test_service.controllers;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import test_service.models.*;
import test_service.services.check.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;

@RestController("CheckController")
public class CheckController {
	private static final Logger logger = LogManager.getLogger(CheckController.class);

	@Autowired
	private CheckService checkService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/check/empty")
	public ResponseEntity<String> checkEmpty() {
		logger.info("Received request, operationId: check.check_empty, method: GET, url: /check/empty");

		checkService.checkEmpty();
		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/check/empty_response")
	public ResponseEntity<String> checkEmptyResponse(@RequestBody String bodyStr) {
		logger.info("Received request, operationId: check.check_empty_response, method: POST, url: /check/empty_response");

		Message requestBody;
		try {
			requestBody = objectMapper.readValue(bodyStr, new TypeReference<Message>() {});
		} catch (IOException e) {
			logger.error("Failed to deserialize request body {}", e.getMessage());
			logger.info("Completed request with status code: {}", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		checkService.checkEmptyResponse(requestBody);
		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check/forbidden")
	public ResponseEntity<String> checkForbidden() {
		logger.info("Received request, operationId: check.check_forbidden, method: GET, url: /check/forbidden");

		var result = checkService.checkForbidden();
		if (result == null) {
			logger.error("Service implementation returned nil");
			logger.info("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (result instanceof CheckForbiddenResponse.Ok) {
			String responseJson = "";
			try {
				responseJson = objectMapper.writeValueAsString(((CheckForbiddenResponse.Ok) result).body);
			} catch (Exception e) {
				logger.error("Failed to serialize JSON: {}", e.getMessage());
				logger.info("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add(CONTENT_TYPE, "application/json");
			logger.info("Completed request with status code: {}", HttpStatus.OK);
			return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
		}
		if (result instanceof CheckForbiddenResponse.Forbidden) {
			logger.info("Completed request with status code: {}", HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		logger.error("No result returned from service implementation");
		logger.info("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/check/same_operation_name")
	public ResponseEntity<String> sameOperationName() {
		logger.info("Received request, operationId: check.same_operation_name, method: GET, url: /check/same_operation_name");

		var result = checkService.sameOperationName();
		if (result == null) {
			logger.error("Service implementation returned nil");
			logger.info("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
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

		logger.error("No result returned from service implementation");
		logger.info("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
