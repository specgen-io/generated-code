package test_client.clients.check

import test_client.models.*

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
