package fr.ebiz.cdb.webapp.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Bertrand Pestre on 07/04/17.
 */
@RestControllerAdvice
public class WebRestControllerAdvice {

    /**
     * Handle InvalidParameterException.
     *
     * @param e e
     * @return e
     */
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleInvalidParameterException(InvalidParameterException e) {
        return e.getErrors().stream()
                .map(objectError -> new Error.Builder().type("Invalid parameter").error(objectError).build())
                .collect(Collectors.toList());
    }

    /**
     * Handle NoSuchElementException.
     *
     * @param e e
     * @return e
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNoSuchElementException(NoSuchElementException e) {
        return new Error.Builder().type("No such element").message(e.getMessage()).build();
    }

    /**
     * Handle PersistenceException.
     *
     * @param e e
     * @return e
     */
    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handlePersistenceException(PersistenceException e) {
        return new Error.Builder().type("Persistence error").message(e.getMessage()).build();
    }

}
