package test_service.services.echo;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_service.models.*;

public interface EchoService {
	String echoBodyString(String body);
	Message echoBody(Message body);
	Message echoQuery(int intQuery, String stringQuery);
	Message echoHeader(int intHeader, String stringHeader);
	Message echoUrlParams(int intUrl, String stringUrl);
	SameOperationNameResponse sameOperationName();
}
