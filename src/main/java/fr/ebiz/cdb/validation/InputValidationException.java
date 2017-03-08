package fr.ebiz.cdb.validation;

/**
 * Created by bpestre on 24/02/17.
 */
public class InputValidationException extends Exception {

    /**
     * Exception raised when input is invalid.
     *
     * @param message the message describing the exception
     */
    public InputValidationException(String message) {
        super(message);
    }
}
