package test_client.clients.echo

import test_client.models.*

interface SameOperationNameResponse {
	class Ok : SameOperationNameResponse {
	}

	class Forbidden : SameOperationNameResponse {
	}
}
