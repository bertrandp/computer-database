package service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.utils.ComputerValidationException;
import service.validation.ComputerValidator;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static service.validation.ComputerValidator.DATE_FORMAT_IS_INVALID;
import static service.validation.ComputerValidator.DATE_IS_NULL;
import static service.validation.ComputerValidator.DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE;
import static service.validation.ComputerValidator.DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE;
import static service.validation.ComputerValidator.ID_IS_EMPTY;
import static service.validation.ComputerValidator.ID_IS_NOT_A_VALID_NUMBER;
import static service.validation.ComputerValidator.ID_IS_NULL;
import static service.validation.ComputerValidator.INTRODUCED_DATE_DOES_NOT_EXISTS;
import static service.validation.ComputerValidator.NAME_IS_EMPTY;
import static service.validation.ComputerValidator.NAME_IS_TOO_LONG;

/**
 * Created by bpestre on 22/02/17.
 */
public class ComputerValidatorTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwExceptionWhenValidateNameWithEmptyData() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(NAME_IS_EMPTY);
        ComputerValidator.validateName("  ");
    }

    @Test
    public void throwExceptionWhenValidateComputerWithInvalidId() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(ID_IS_NOT_A_VALID_NUMBER);
        ComputerValidator.validateId("invalid input");
    }

    @Test
    public void throwExceptionWhenValidateComputerWithEmptyId() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(ID_IS_EMPTY);
        ComputerValidator.validateId("  ");
    }

    @Test
    public void throwExceptionWhenValidateNameWithInvalidData() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(NAME_IS_TOO_LONG);
        ComputerValidator.validateName("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz ");
    }

    @Test
    public void validateNameWithValidData() throws ComputerValidationException {
        assertTrue(ComputerValidator.validateName("Valid name"));
    }

    @Test
    public void throwExceptionWhenValidateDiscontinuedDateWithSameDates() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE);
        ComputerValidator.validateDiscontinuedDate(LocalDate.now(), LocalDate.now());
    }

    @Test
    public void throwExceptionWhenValidateDiscontinuedDateWithInvalidDate() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE);
        ComputerValidator.validateDiscontinuedDate(LocalDate.now(), LocalDate.MAX);
    }

    @Test
    public void throwExceptionWhenValidateDiscontinuedDateWithNullIntroducedDate() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(INTRODUCED_DATE_DOES_NOT_EXISTS);
        ComputerValidator.validateDiscontinuedDate(LocalDate.now(), null);
    }

    @Test
    public void validateDiscontinuedDateWithValidDate() throws ComputerValidationException {
        assertTrue(ComputerValidator.validateDiscontinuedDate(LocalDate.now(), LocalDate.MIN));
    }

    @Test
    public void throwExceptionWhenValidateDateWithInvalidDate() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(DATE_FORMAT_IS_INVALID);
        ComputerValidator.validateInputDate("invalid date");
    }

    @Test
    public void throwExceptionWhenValidateDateWithNull() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(DATE_IS_NULL);
        ComputerValidator.validateInputDate(null);
    }

    @Test
    public void validateDateWithValidDate() throws ComputerValidationException {
        assertNotNull(ComputerValidator.validateInputDate("01/01/2000"));
    }

    @Test
    public void throwExceptionWhenValidateCompanyIdWithInvalidId() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(ID_IS_NOT_A_VALID_NUMBER);
        ComputerValidator.validateCompanyId("invalid id");
    }

    @Test
    public void throwExceptionWhenValidateCompanyIdWithNull() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(ID_IS_NULL);
        ComputerValidator.validateCompanyId(null);
    }

    @Test
    public void validateCompanyIdWithValidId() throws ComputerValidationException {
        assertNotNull(ComputerValidator.validateCompanyId("1"));
    }
}
