package fr.ebiz.cdb.service.validation;

import fr.ebiz.cdb.service.exception.InputValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.ebiz.cdb.service.validation.ComputerValidator.ID_IS_EMPTY;
import static fr.ebiz.cdb.service.validation.ComputerValidator.ID_IS_NOT_A_VALID_NUMBER;
import static fr.ebiz.cdb.service.validation.ComputerValidator.ID_IS_NULL;

/**
 * Created by bpestre on 24/02/17.
 */
public class InputValidation {

    /**
     * Validate the given string and return an Integer if it is one.
     *
     * @param input the input string to validate
     * @return the integer
     * @throws InputValidationException exception raised if the input is invalid
     */
    public static Integer validateInputInteger(String input) throws InputValidationException {
        if (input == null) {
            throw new InputValidationException(ID_IS_NULL);
        } else if (input.trim().isEmpty()) {
            throw new InputValidationException(ID_IS_EMPTY);
        } else {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(input);
            if (m.find()) {
                throw new InputValidationException(ID_IS_NOT_A_VALID_NUMBER);
            }
        }
        return Integer.valueOf(input);
    }
}
