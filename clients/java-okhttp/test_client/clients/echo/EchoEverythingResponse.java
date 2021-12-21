package test_client.clients.echo;

import test_client.models.*;

public interface EchoEverythingResponse {
	class Ok implements EchoEverythingResponse {
		public Everything body;

		public Ok() {
		}

		public Ok(Everything body) {
			this.body = body;
		}
	}

	class Forbidden implements EchoEverythingResponse {
	}
}
