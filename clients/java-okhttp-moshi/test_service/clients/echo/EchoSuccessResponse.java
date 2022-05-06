package test_service.clients.echo;

import test_service.models.*;

public interface EchoSuccessResponse {
	class Ok implements EchoSuccessResponse {
		public OkResult body;

		public Ok() {
		}

		public Ok(OkResult body) {
			this.body = body;
		}
	}

	class Created implements EchoSuccessResponse {
		public CreatedResult body;

		public Created() {
		}

		public Created(CreatedResult body) {
			this.body = body;
		}
	}

	class Accepted implements EchoSuccessResponse {
		public AcceptedResult body;

		public Accepted() {
		}

		public Accepted(AcceptedResult body) {
			this.body = body;
		}
	}
}
