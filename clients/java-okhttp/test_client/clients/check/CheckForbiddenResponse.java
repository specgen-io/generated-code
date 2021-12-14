package test_client.clients.check;

import test_client.models.*;

public interface CheckForbiddenResponse {
	class Ok implements CheckForbiddenResponse {
		public Message body;

		public Ok() {
		}

		public Ok(Message body) {
			this.body = body;
		}
	}

	class Forbidden implements CheckForbiddenResponse {
	}
}
