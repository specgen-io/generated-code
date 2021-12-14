package test_client.clients.echo;

import test_client.models.*;

public interface SameOperationNameResponse {
	class Ok implements SameOperationNameResponse {
	}

	class Forbidden implements SameOperationNameResponse {
	}
}
