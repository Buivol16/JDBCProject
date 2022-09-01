package ua.denis.jdbcproject.loginapp.exception;

public class EmptyFieldException extends Exception {
    EmptyFieldException(String message) {
        super(message);
    }
}
