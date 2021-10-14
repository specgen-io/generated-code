package test_service.controllers;

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
import test_service.models.*;
import test_service.services.echo.*;

@RestController("EchoController")
public class EchoController {
	final EchoService echoService;

	ObjectMapper objectMapper;

	public EchoController(EchoService echoService) {
		this.echoService = echoService;
		this.objectMapper = new ObjectMapper();
		Json.setupObjectMapper(this.objectMapper);
	}

	@PostMapping("/echo/body")
	public ResponseEntity<String> echoBodyController(@RequestBody String jsonStr) throws IOException {
		var requestBody = objectMapper.readValue(jsonStr, Message.class);
		var result = echoService.echoBody(requestBody);

		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");
		String responseJson = objectMapper.writeValueAsString(result);

		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/query")
	public ResponseEntity<String> echoQueryController(@RequestParam(name = "int_query") int intQuery, @RequestParam(name = "string_query") String stringQuery) throws IOException {
		var result = echoService.echoQuery(intQuery, stringQuery);

		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");
		String responseJson = objectMapper.writeValueAsString(result);

		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/header")
	public ResponseEntity<String> echoHeaderController(@RequestHeader(name = "Int-Header") int intHeader, @RequestHeader(name = "String-Header") String stringHeader) throws IOException {
		var result = echoService.echoHeader(intHeader, stringHeader);

		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");
		String responseJson = objectMapper.writeValueAsString(result);

		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/url_params/{int_url}/{string_url}")
	public ResponseEntity<String> echoUrlParamsController(@PathVariable(name = "int_url") int intUrl, @PathVariable(name = "string_url") String stringUrl) throws IOException {
		var result = echoService.echoUrlParams(intUrl, stringUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");
		String responseJson = objectMapper.writeValueAsString(result);

		return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
	}

	@GetMapping("/echo/same_operation_name")
	public ResponseEntity<String> sameOperationNameController() throws IOException {
		var result = echoService.sameOperationName();

		if (result instanceof SameOperationNameResponseOk) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		if (result instanceof SameOperationNameResponseForbidden) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
