package test_service.clients.echo

import test_service.models.*

interface EchoEverythingResponse {
	class Ok : EchoEverythingResponse {
		private lateinit var body: Everything

		constructor()

		constructor(body: Everything) {
			this.body = body
		}
	}

	class Forbidden : EchoEverythingResponse {
	}
}
