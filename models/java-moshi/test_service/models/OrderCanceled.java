package test_service.models;

import com.squareup.moshi.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.io.*;

public class OrderCanceled {

	@Json(name = "id")
	private UUID id;

	public OrderCanceled(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderCanceled)) return false;
		OrderCanceled that = (OrderCanceled) o;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return String.format("OrderCanceled{id=%s}", id);
	}
}
