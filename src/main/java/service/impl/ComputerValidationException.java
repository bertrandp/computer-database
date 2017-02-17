package service.impl;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerValidationException extends Exception {

    /**
     * Exception raised when input related to computer is invalid.
     *
     * @param message the message describing the exception
     */
    public ComputerValidationException(String message) {
        super(message);
    }
}
