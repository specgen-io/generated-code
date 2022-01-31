package test_client.clients.check

import test_client.models.*

interface SameOperationNameResponse {
	class Ok : SameOperationNameResponse {
	}

	class Forbidden : SameOperationNameResponse {
	}
}
