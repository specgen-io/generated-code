package test_service.models;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public enum Choice {
	@Json(name = "One") FIRST_CHOICE,
	@Json(name = "Two") SECOND_CHOICE,
	@Json(name = "Three") THIRD_CHOICE,
}
