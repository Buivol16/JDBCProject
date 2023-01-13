package ua.denis.jdbcproject.client.loginapp.exception;

public class OccupiedNameException extends Exception{
    public OccupiedNameException(String message) {
        super(message);
    }
}
