package test_service.json;

import com.squareup.moshi.Moshi;
import test_service.json.adapters.*;

public class Json {
	public static void setupMoshiAdapters(Moshi.Builder moshiBuilder) {
		moshiBuilder
			.add(new BigDecimalAdapter())
			.add(new UuidAdapter())
			.add(new LocalDateAdapter())
			.add(new LocalDateTimeAdapter());
	}
}