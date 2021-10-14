package test_service.services.check;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_service.models.*;

public interface CheckService {
	void checkEmpty();
	void checkQuery(String pString, String pStringOpt, String[] pStringArray, LocalDate pDate, LocalDate[] pDateArray, LocalDateTime pDatetime, int pInt, long pLong, BigDecimal pDecimal, Choice pEnum, String pStringDefaulted);
	void checkUrlParams(long intUrl, String stringUrl, float floatUrl, boolean boolUrl, UUID uuidUrl, BigDecimal decimalUrl, LocalDate dateUrl, Choice enumUrl);
	CheckForbiddenResponse checkForbidden();
	SameOperationNameResponse sameOperationName();
}
