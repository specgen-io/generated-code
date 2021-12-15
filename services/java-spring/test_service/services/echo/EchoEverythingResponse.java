package test_service.services.echo;

import test_service.models.*;

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
