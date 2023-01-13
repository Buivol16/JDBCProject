package ua.denis.jdbcproject.client.loginapp.exception;

public class SessionIsExpiredException extends Exception{
    public SessionIsExpiredException(String message) {
        super(message);
    }
}
