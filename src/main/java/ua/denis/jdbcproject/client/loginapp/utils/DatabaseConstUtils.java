package ua.denis.jdbcproject.client.loginapp.utils;

import java.time.format.DateTimeFormatter;

public final class DatabaseConstUtils {
    public static class SessionColumnConsts {
        public final static int MINUTES_PLUS = 30;
        public final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern(ConstUtils.TIME_PATTERN);
    }
}
