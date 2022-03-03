package test_service.services.v2;

import org.springframework.stereotype.Service;
import test_service.v2.models.*;
import test_service.v2.services.echo.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

@Service("EchoServiceV2")
public class EchoServiceImpl implements EchoService {
	@Override
	public Message echoBodyModel(Message body) {
		throw new UnsupportedOperationException("Implementation has not added yet");
	}
}
