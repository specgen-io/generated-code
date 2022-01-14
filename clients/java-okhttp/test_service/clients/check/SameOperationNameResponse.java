package test_service.clients.check;

import test_service.models.*;

public interface SameOperationNameResponse {
	class Ok implements SameOperationNameResponse {
	}

	class Forbidden implements SameOperationNameResponse {
	}
}
