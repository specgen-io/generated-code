package test_service.services_implementations;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.Service;

import test_service.models.*;
import test_service.services.echo.*;

@Service("EchoService")
public class EchoService implements IEchoService {
	@Override
	public Message echoBody(Message body) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public Message echoQuery(int intQuery, String stringQuery) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public Message echoHeader(int intHeader, String stringHeader) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public Message echoUrlParams(int intUrl, String stringUrl) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
	@Override
	public void sameOperationName() {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
}
