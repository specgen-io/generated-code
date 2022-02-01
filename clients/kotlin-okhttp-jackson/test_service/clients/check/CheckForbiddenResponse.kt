package test_service.clients.check

import test_service.models.*

interface CheckForbiddenResponse {
	class Ok : CheckForbiddenResponse {
		private lateinit var body: Message

		constructor()

		constructor(body: Message) {
			this.body = body
		}
	}

	class Forbidden : CheckForbiddenResponse {
	}
}
