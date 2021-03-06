package test_service.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public enum ErrorLocation {
	@Json(name = "query") QUERY,
	@Json(name = "header") HEADER,
	@Json(name = "body") BODY,
}
