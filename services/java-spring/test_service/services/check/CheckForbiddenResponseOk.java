package test_service.services.check;

import test_service.models.*;

public class CheckForbiddenResponseOk implements CheckForbiddenResponse {
	public Message ok;

	public CheckForbiddenResponseOk() {
	}

	public CheckForbiddenResponseOk(Message ok) {
		this.ok = ok;
	}
}
