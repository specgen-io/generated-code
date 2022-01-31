package test_client.clients.echo

import test_client.models.*

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
