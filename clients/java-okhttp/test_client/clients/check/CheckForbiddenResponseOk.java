package test_client.clients.check;

import test_client.models.*;

public class CheckForbiddenResponseOk implements CheckForbiddenResponse {
	public Message ok;

	public CheckForbiddenResponseOk() {
	}

	public CheckForbiddenResponseOk(Message ok) {
		this.ok = ok;
	}
}
