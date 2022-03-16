package test_service.clients.check

import test_service.models.*

interface CheckForbiddenResponse {
	class Ok(var body: Message) : CheckForbiddenResponse

	class Forbidden : CheckForbiddenResponse
}
