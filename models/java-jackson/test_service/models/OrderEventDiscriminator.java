package test_service.models;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "_type"
)
@JsonSubTypes({
	@Type(value = OrderEventDiscriminator.Created.class, name = "created"),
	@Type(value = OrderEventDiscriminator.Changed.class, name = "changed"),
	@Type(value = OrderEventDiscriminator.Canceled.class, name = "canceled"),
})
public interface OrderEventDiscriminator {
	class Created implements OrderEventDiscriminator {
		@JsonUnwrapped
		public OrderCreated data;

		public Created() {
		}

		public Created(OrderCreated data) {
			if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
			this.data = data;
		}

		public OrderCreated getData() {
			return data;
		}

		public void setData(OrderCreated data) {
			if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
			this.data = data;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Created)) return false;
			Created that = (Created) o;
			return Objects.equals(getData(), that.getData());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getData());
		}

		@Override
		public String toString() {
			return String.format("Created{data=%s}", data);
		}
	}

	class Changed implements OrderEventDiscriminator {
		@JsonUnwrapped
		public OrderChanged data;

		public Changed() {
		}

		public Changed(OrderChanged data) {
			if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
			this.data = data;
		}

		public OrderChanged getData() {
			return data;
		}

		public void setData(OrderChanged data) {
			if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
			this.data = data;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Changed)) return false;
			Changed that = (Changed) o;
			return Objects.equals(getData(), that.getData());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getData());
		}

		@Override
		public String toString() {
			return String.format("Changed{data=%s}", data);
		}
	}

	class Canceled implements OrderEventDiscriminator {
		@JsonUnwrapped
		public OrderCanceled data;

		public Canceled() {
		}

		public Canceled(OrderCanceled data) {
			if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
			this.data = data;
		}

		public OrderCanceled getData() {
			return data;
		}

		public void setData(OrderCanceled data) {
			if (data == null) { throw new IllegalArgumentException("null value is not allowed"); }
			this.data = data;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Canceled)) return false;
			Canceled that = (Canceled) o;
			return Objects.equals(getData(), that.getData());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getData());
		}

		@Override
		public String toString() {
			return String.format("Canceled{data=%s}", data);
		}
	}
}
