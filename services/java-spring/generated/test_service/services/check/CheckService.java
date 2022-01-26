package test_service.services.check;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;
import test_service.models.*;

public interface CheckService {
	void checkEmpty();
	void checkEmptyResponse(Message body);
	CheckForbiddenResponse checkForbidden();
	SameOperationNameResponse sameOperationName();
}
