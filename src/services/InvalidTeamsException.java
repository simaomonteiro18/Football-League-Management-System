package services;

public class InvalidTeamsException extends RuntimeException {
    public InvalidTeamsException(String message) {
        super(message);
    }
}
