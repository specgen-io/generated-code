package test_service.services.check;

import test_service.models.*;

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
