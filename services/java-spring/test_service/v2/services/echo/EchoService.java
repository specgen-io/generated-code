package test_service.v2.services.echo;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import test_service.v2.models.*;

public interface EchoService {
	Message echoBody(Message body);
}