package fr.ebiz.cdb.service.exception;

/**
 * Created by bpestre on 24/02/17.
 */
public class ComputerException extends Exception {

    /**
     * Exception raised when input related to computer is invalid.
     *
     * @param message the message describing the exception
     */
    public ComputerException(String message) {
        super(message);
    }

    /**
     * Exception raised when input related to computer is invalid.
     *
     * @param e the exception
     */
    public ComputerException(Exception e) {
        super(e);
    }
}
