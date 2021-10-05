package test_service.services_implementations;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.Service;

import test_service.models.*;
import test_service.services.check.*;

@Service("CheckService")
public class CheckService implements ICheckService {
	@Override
	public void checkEmpty() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public void checkQuery(String pString, String pStringOpt, String[] pStringArray, LocalDate pDate, LocalDate[] pDateArray, LocalDateTime pDatetime, int pInt, long pLong, BigDecimal pDecimal, Choice pEnum, String pStringDefaulted) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public void checkUrlParams(long intUrl, String stringUrl, float floatUrl, boolean boolUrl, UUID uuidUrl, BigDecimal decimalUrl, LocalDate dateUrl, Choice enumUrl) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public CheckForbiddenResponse checkForbidden() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public void sameOperationName() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
}
