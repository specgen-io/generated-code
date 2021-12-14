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

	@GetMapping("/check/query")
	public ResponseEntity<String> checkQueryController(@RequestParam(name = "p_string") String pString, @RequestParam(name = "p_string_opt", required = false) String pStringOpt, @RequestParam(name = "p_string_array") String[] pStringArray, @RequestParam(name = "p_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pDate, @RequestParam(name = "p_date_array") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate[] pDateArray, @RequestParam(name = "p_datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pDatetime, @RequestParam(name = "p_int") int pInt, @RequestParam(name = "p_long") long pLong, @RequestParam(name = "p_decimal") BigDecimal pDecimal, @RequestParam(name = "p_enum") Choice pEnum, @RequestParam(name = "p_string_defaulted", defaultValue = "the default value") String pStringDefaulted) throws IOException {
		logger.info("Received request, operationId: check.check_query, method: GET, url: /check/query");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		checkService.checkQuery(pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted);

		logger.info("Completed request with status code: {}", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check/url_params/{int_url}/{string_url}/{float_url}/{bool_url}/{uuid_url}/{decimal_url}/{date_url}/{enum_url}")
	public ResponseEntity<String> checkUrlParamsController(@PathVariable(name = "int_url") long intUrl, @PathVariable(name = "string_url") String stringUrl, @PathVariable(name = "float_url") float floatUrl, @PathVariable(name = "bool_url") boolean boolUrl, @PathVariable(name = "uuid_url") UUID uuidUrl, @PathVariable(name = "decimal_url") BigDecimal decimalUrl, @PathVariable(name = "date_url") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateUrl, @PathVariable(name = "enum_url") Choice enumUrl) throws IOException {
		logger.info("Received request, operationId: check.check_url_params, method: GET, url: /check/url_params/{int_url}/{string_url}/{float_url}/{bool_url}/{uuid_url}/{decimal_url}/{date_url}/{enum_url}");
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, "application/json");

		checkService.checkUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl, enumUrl);

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

		if (result instanceof CheckForbiddenResponseOk) {
			String responseJson = objectMapper.writeValueAsString(((CheckForbiddenResponseOk) result).ok);
			logger.info("Completed request with status code: {}", HttpStatus.OK);
			return new ResponseEntity<>(responseJson, headers, HttpStatus.OK);
		}

		if (result instanceof CheckForbiddenResponseForbidden) {
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

		if (result instanceof SameOperationNameResponseOk) {
			logger.info("Completed request with status code: {}", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		if (result instanceof SameOperationNameResponseForbidden) {
			logger.info("Completed request with status code: {}", HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		logger.error("Completed request with status code: {}", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
