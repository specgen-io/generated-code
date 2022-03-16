package test_service.clients.echo

import test_service.models.*

interface EchoEverythingResponse {
	class Ok(var body: Everything) : EchoEverythingResponse

	class Forbidden : EchoEverythingResponse
}
