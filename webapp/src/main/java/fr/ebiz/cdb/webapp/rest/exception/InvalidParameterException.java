package fr.ebiz.cdb.webapp.rest.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by Bertrand Pestre on 07/04/17.
 */
public class InvalidParameterException extends IllegalArgumentException {

    private List<ObjectError> allErrors;

    /**
     * Constructor.
     *
     * @param allErrors allErrors
     */
    public InvalidParameterException(List<ObjectError> allErrors) {
        this.allErrors = allErrors;
    }

    public List<ObjectError> getErrors() {
        return allErrors;
    }
}
