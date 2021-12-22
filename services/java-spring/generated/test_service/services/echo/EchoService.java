package test_service.services.echo;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_service.models.*;

public interface EchoService {
	String echoBodyString(String body);
	Message echoBody(Message body);
	Parameters echoQuery(int intQuery, long longQuery, float floatQuery, double doubleQuery, BigDecimal decimalQuery, boolean boolQuery, String stringQuery, String stringOptQuery, String stringDefaultedQuery, String[] stringArrayQuery, UUID uuidQuery, LocalDate dateQuery, LocalDate[] dateArrayQuery, LocalDateTime datetimeQuery, Choice enumQuery);
	Parameters echoHeader(int intHeader, long longHeader, float floatHeader, double doubleHeader, BigDecimal decimalHeader, boolean boolHeader, String stringHeader, String stringOptHeader, String stringDefaultedHeader, String[] stringArrayHeader, UUID uuidHeader, LocalDate dateHeader, LocalDate[] dateArrayHeader, LocalDateTime datetimeHeader, Choice enumHeader);
	UrlParameters echoUrlParams(int intUrl, long longUrl, float floatUrl, double doubleUrl, BigDecimal decimalUrl, boolean boolUrl, String stringUrl, UUID uuidUrl, LocalDate dateUrl, LocalDateTime datetimeUrl, Choice enumUrl);
	EchoEverythingResponse echoEverything(Message body, float floatQuery, boolean boolQuery, UUID uuidHeader, LocalDateTime datetimeHeader, LocalDate dateUrl, BigDecimal decimalUrl);
	SameOperationNameResponse sameOperationName();
}
