package ua.denis.jdbcproject.exception;

public class TooManyCharactersException extends Exception{
    public TooManyCharactersException(String message) {
        super(message);
    }
}
