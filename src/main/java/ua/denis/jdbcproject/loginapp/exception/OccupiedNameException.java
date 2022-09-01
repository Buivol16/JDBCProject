package ua.denis.jdbcproject.loginapp.exception;

public class OccupiedNameException extends Exception{
    public OccupiedNameException(String message) {
        super(message);
    }
}
