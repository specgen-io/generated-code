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

import test_service.models.Jsoner;
import test_service.models.*;
import test_service.services.check.*;

@RestController("CheckController")
public class CheckController {
	final ICheckService checkService;

	public CheckController(ICheckService checkService) {
		this.checkService = checkService;
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/check/empty")
	public ResponseEntity<String> checkEmptyController() throws IOException {
		checkService.checkEmpty();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check/query")
	public ResponseEntity<String> checkQueryController(@RequestParam("p_string") String pString, @RequestParam("p_string_opt") String pStringOpt, @RequestParam("p_string_array") String[] pStringArray, @RequestParam("p_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pDate, @RequestParam("p_date_array") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate[] pDateArray, @RequestParam("p_datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pDatetime, @RequestParam("p_int") int pInt, @RequestParam("p_long") long pLong, @RequestParam("p_decimal") BigDecimal pDecimal, @RequestParam("p_enum") Choice pEnum, @RequestParam("p_string_defaulted") String pStringDefaulted) throws IOException {
		checkService.checkQuery(pString, pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check/url_params/{int_url}/{string_url}/{float_url}/{bool_url}/{uuid_url}/{decimal_url}/{date_url}/{enum_url}")
	public ResponseEntity<String> checkUrlParamsController(@PathVariable("int_url") long intUrl, @PathVariable("string_url") String stringUrl, @PathVariable("float_url") float floatUrl, @PathVariable("bool_url") boolean boolUrl, @PathVariable("uuid_url") UUID uuidUrl, @PathVariable("decimal_url") BigDecimal decimalUrl, @PathVariable("date_url") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateUrl, @PathVariable("enum_url") Choice enumUrl) throws IOException {
		checkService.checkUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl, enumUrl);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check/forbidden")
	public ResponseEntity<String> checkForbiddenController() throws IOException {
		var result = checkService.checkForbidden();

		if (result instanceof CheckForbiddenResponseOk) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		if (result instanceof CheckForbiddenResponseForbidden) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/check/same_operation_name")
	public ResponseEntity<String> sameOperationNameController() throws IOException {
		var result = checkService.sameOperationName();

		if (result instanceof SameOperationNameResponseOk) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		if (result instanceof SameOperationNameResponseForbidden) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
