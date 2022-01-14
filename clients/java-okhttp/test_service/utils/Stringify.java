package test_service.utils;

public class Stringify {
    public static String paramToString(Object value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}