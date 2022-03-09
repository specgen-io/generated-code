package test_service.services;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;
import org.springframework.stereotype.Service;
import test_service.models.*;
import test_service.services.check.*;

@Service("CheckService")
public class CheckServiceImpl implements CheckService {
	@Override
	public void checkEmpty() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public void checkEmptyResponse(Message body) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public CheckForbiddenResponse checkForbidden() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public SameOperationNameResponse sameOperationName() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
}
