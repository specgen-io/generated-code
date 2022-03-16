package test_service.clients.echo

import test_service.models.*

interface SameOperationNameResponse {
	class Ok : SameOperationNameResponse

	class Forbidden : SameOperationNameResponse
}
