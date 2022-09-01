package ua.denis.jdbcproject.loginapp.exception;

public class SessionIsExpiredException extends Exception{
    public SessionIsExpiredException(String message) {
        super(message);
    }
}
