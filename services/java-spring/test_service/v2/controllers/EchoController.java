package test_service.v2.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.io.IOException;
import java.time.*;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;

import test_service.models.Json;
import test_service.v2.models.*;
import test_service.v2.services.echo.*;

@RestController("EchoControllerV2")
public class EchoController {
	final EchoService echoService;

	ObjectMapper objectMapper;

	public EchoController(EchoService echoService) {
		this.echoService = echoService;
		this.objectMapper = new ObjectMapper();
		Json.setupObjectMapper(this.objectMapper);
	}

	@PostMapping("v2/echo/body")
	public ResponseEntity<String> echoBodyController(@RequestBody String jsonStr) throws IOException {
		var requestBody = objectMapper.readValue(jsonStr, Message.class);
		var result = echoService.echoBody(requestBody);

		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");
		String responseJson = objectMapper.writeValueAsString(result);

		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}
}
