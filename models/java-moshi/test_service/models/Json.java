package test_service.models;

import com.squareup.moshi.Moshi;
import test_service.json.adapters.*;

public class Json {
	public static void setupMoshiAdapters(Moshi.Builder moshiBuilder) {
		moshiBuilder
			.add(new UnwrapFieldAdapterFactory(OrderEventWrapper.Created.class))
			.add(new UnwrapFieldAdapterFactory(OrderEventWrapper.Changed.class))
			.add(new UnwrapFieldAdapterFactory(OrderEventWrapper.Canceled.class))
			.add(UnionAdapterFactory.of(OrderEventWrapper.class)
				.withSubtype(OrderEventWrapper.Created.class, "created")
				.withSubtype(OrderEventWrapper.Changed.class, "changed")
				.withSubtype(OrderEventWrapper.Canceled.class, "canceled")
			);
		moshiBuilder
			.add(new UnwrapFieldAdapterFactory(OrderEventDiscriminator.Created.class))
			.add(new UnwrapFieldAdapterFactory(OrderEventDiscriminator.Changed.class))
			.add(new UnwrapFieldAdapterFactory(OrderEventDiscriminator.Canceled.class))
			.add(UnionAdapterFactory.of(OrderEventDiscriminator.class).withDiscriminator("_type")
				.withSubtype(OrderEventDiscriminator.Created.class, "created")
				.withSubtype(OrderEventDiscriminator.Changed.class, "changed")
				.withSubtype(OrderEventDiscriminator.Canceled.class, "canceled")
			);
	}
}
