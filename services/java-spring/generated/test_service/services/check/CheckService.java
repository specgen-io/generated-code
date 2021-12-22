package test_service.services.check;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_service.models.*;

public interface CheckService {
	void checkEmpty();
	CheckForbiddenResponse checkForbidden();
	SameOperationNameResponse sameOperationName();
}
