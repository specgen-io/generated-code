package test_service.v2.controllers;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import test_service.v2.models.*;
import test_service.v2.services.echo.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;

@RestController("EchoControllerV2")
public class EchoController {
	private static final Logger logger = LogManager.getLogger(EchoController.class);

	@Autowired
	private EchoService echoService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/v2/echo/body_model")
	public ResponseEntity<String> echoBodyModel(@RequestBody String bodyStr) {
		logger.info("Received request, operationId: echo.echo_body_model, method: POST, url: /v2/echo/body_model");

		Message requestBody;
		try {
			requestBody = objectMapper.readValue(bodyStr, new TypeReference<Message>() {});
		} catch (IOException e) {
			logger.error("Failed to deserialize request body {}", e.getMessage());
			logger.info("Completed request with status code: {}", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var result = echoService.echoBodyModel(requestBody);
		if (result == null) {
			logger.error("Service implementation returned nil");
			logger.info("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String responseJson = "";
		try {
			responseJson = objectMapper.writeValueAsString(result);
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
}
