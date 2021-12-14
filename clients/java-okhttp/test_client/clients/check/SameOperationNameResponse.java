package test_client.clients.check;

import test_client.models.*;

public interface SameOperationNameResponse {
	class Ok implements SameOperationNameResponse {
	}

	class Forbidden implements SameOperationNameResponse {
	}
}
