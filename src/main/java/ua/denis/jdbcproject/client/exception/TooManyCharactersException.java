package ua.denis.jdbcproject.client.exception;

public class TooManyCharactersException extends Exception{
    public TooManyCharactersException(String message) {
        super(message);
    }
}
