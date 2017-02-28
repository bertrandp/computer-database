package fr.ebiz.cdb.service.exception;

/**
 * Created by bpestre on 24/02/17.
 */
public class CompanyException extends Exception {

    /**
     * Exception raised when input related to company is invalid.
     *
     * @param message the message describing the exception
     */
    public CompanyException(String message) {
        super(message);
    }

    /**
     * Exception raised when input related to company is invalid.
     *
     * @param e the exception
     */
    public CompanyException(Exception e) {
        super(e);
    }
}
