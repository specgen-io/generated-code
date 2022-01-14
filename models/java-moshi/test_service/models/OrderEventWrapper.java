package test_service.models;

import com.squareup.moshi.Json;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public interface OrderEventWrapper {
	class Created implements OrderEventWrapper {
		public OrderCreated data;

		public Created() {
		}

		public Created(OrderCreated data) {
			this.data = data;
		}

		public OrderCreated getData() {
			return data;
		}

		public void setData(OrderCreated data) {
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

	class Changed implements OrderEventWrapper {
		public OrderChanged data;

		public Changed() {
		}

		public Changed(OrderChanged data) {
			this.data = data;
		}

		public OrderChanged getData() {
			return data;
		}

		public void setData(OrderChanged data) {
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

	class Canceled implements OrderEventWrapper {
		public OrderCanceled data;

		public Canceled() {
		}

		public Canceled(OrderCanceled data) {
			this.data = data;
		}

		public OrderCanceled getData() {
			return data;
		}

		public void setData(OrderCanceled data) {
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