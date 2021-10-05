package test_service.services_implementations.v2;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.Service;

import test_service.v2.models.*;
import test_service.v2.services.echo.*;

@Service("EchoServiceV2")
public class EchoService implements IEchoService {
	@Override
	public Message echoBody(Message body) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
}
