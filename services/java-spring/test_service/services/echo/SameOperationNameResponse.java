package test_service.services.echo;

import test_service.models.*;

public interface SameOperationNameResponse {
	class Ok implements SameOperationNameResponse {
	}

	class Forbidden implements SameOperationNameResponse {
	}
}
