package test_service.services;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.Service;

import test_service.models.*;
import test_service.services.echo.*;

@Service("EchoService")
public class EchoServiceImpl implements EchoService {
	@Override
	public String echoBodyString(String body) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public Message echoBody(Message body) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public Parameters echoQuery(int intQuery, long longQuery, float floatQuery, double doubleQuery, BigDecimal decimalQuery, boolean boolQuery, String stringQuery, String stringOptQuery, String stringDefaultedQuery, String[] stringArrayQuery, UUID uuidQuery, LocalDate dateQuery, LocalDate[] dateArrayQuery, LocalDateTime datetimeQuery, Choice enumQuery) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public Parameters echoHeader(int intHeader, long longHeader, float floatHeader, double doubleHeader, BigDecimal decimalHeader, boolean boolHeader, String stringHeader, String stringOptHeader, String stringDefaultedHeader, String[] stringArrayHeader, UUID uuidHeader, LocalDate dateHeader, LocalDate[] dateArrayHeader, LocalDateTime datetimeHeader, Choice enumHeader) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public UrlParameters echoUrlParams(int intUrl, long longUrl, float floatUrl, double doubleUrl, BigDecimal decimalUrl, boolean boolUrl, String stringUrl, UUID uuidUrl, LocalDate dateUrl, LocalDateTime datetimeUrl, Choice enumUrl) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public EchoEverythingResponse echoEverything(Message body, float floatQuery, boolean boolQuery, UUID uuidHeader, LocalDateTime datetimeHeader, LocalDate dateUrl, BigDecimal decimalUrl) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public SameOperationNameResponse sameOperationName() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
}