package test_service.clients.echo

import test_service.models.*

interface EchoSuccessResponse {
	class Ok(var body: OkResult) : EchoSuccessResponse

	class Created(var body: CreatedResult) : EchoSuccessResponse

	class Accepted(var body: AcceptedResult) : EchoSuccessResponse
}
