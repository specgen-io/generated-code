package test_service.json.adapters;

import com.squareup.moshi.*;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Set;

public final class UnwrapFieldAdapterFactory<T> implements JsonAdapter.Factory {
    final Class<T> type;

    public UnwrapFieldAdapterFactory(Class<T> type) {
        this.type = type;
    }

    @Nullable
    @Override
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        if (Types.getRawType(type) != this.type || !annotations.isEmpty()) {
            return null;
        }

        final Field[] fields = this.type.getDeclaredFields();
        if (fields.length != 1) {
            throw new RuntimeException("Type "+type.getTypeName()+" has "+fields.length+" fields, unwrap adapter can be used only with single-field types");
        }
        var field = fields[0];

        Constructor<T> constructor;
        try {
            constructor = this.type.getDeclaredConstructor(field.getType());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Type "+type.getTypeName()+" does not have constructor with single parameter of type "+ field.getType().getName()+", it's required for unwrap adapter");
        }

        JsonAdapter<?> fieldAdapter = moshi.adapter(field.getType());
        return new UnwrapFieldAdapter(constructor, field, fieldAdapter);
    }

    public class UnwrapFieldAdapter<O, I> extends JsonAdapter<Object> {
        private Constructor<O> constructor;
        private Field field;
        private JsonAdapter<I> fieldAdapter;

        public UnwrapFieldAdapter(Constructor<O> constructor, Field field, JsonAdapter<I> fieldAdapter) {
            this.constructor = constructor;
            this.field = field;
            this.fieldAdapter = fieldAdapter;
        }

        @Override
        public Object fromJson(JsonReader reader) throws IOException {
            I fieldValue = fieldAdapter.fromJson(reader);
            try {
                return constructor.newInstance(fieldValue);
            } catch (Throwable e) {
                throw new IOException("Failed to create object with constructor "+constructor.getName(), e);
            }
        }

        @Override
        public void toJson(JsonWriter writer, Object value) throws IOException {
            I fieldValue;
            try {
                fieldValue = (I)field.get(value);
            } catch (IllegalAccessException e) {
                throw new IOException("Failed to get value of field "+field.getName(), e);
            }
            fieldAdapter.toJson(writer, fieldValue);
        }

        @Override
        public String toString() {
            return "UnwrapFieldAdapter(" + field.getName() + ")";
        }
    }
}