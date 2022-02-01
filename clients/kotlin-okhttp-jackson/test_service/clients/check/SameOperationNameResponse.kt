package test_service.clients.check

import test_service.models.*

interface SameOperationNameResponse {
	class Ok : SameOperationNameResponse {
	}

	class Forbidden : SameOperationNameResponse {
	}
}
