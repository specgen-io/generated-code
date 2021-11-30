package test_service.v2.controllers;

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
import test_service.v2.models.*;
import test_service.v2.services.echo.*;

@RestController("EchoControllerV2")
public class EchoController {
	private static final Logger logger = LogManager.getLogger(EchoController.class);

	@Autowired
	private EchoService echoService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/v2/echo/body")
	public ResponseEntity<String> echoBodyController(@RequestBody String bodyStr) throws IOException {
		logger.info("Received request, operationId: echo.echo_body, method: POST, url: /v2/echo/body");
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
		String responseJson = objectMapper.writeValueAsString(result);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}
}
