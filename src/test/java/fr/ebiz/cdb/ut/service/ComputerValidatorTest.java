package fr.ebiz.cdb.ut.service;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import fr.ebiz.cdb.service.validation.InputValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static fr.ebiz.cdb.dto.ComputerPagerDTO.COLUMN.INTRODUCED;
import static fr.ebiz.cdb.dto.ComputerPagerDTO.COLUMN.NAME;
import static fr.ebiz.cdb.dto.ComputerPagerDTO.ORDER.ASC;
import static fr.ebiz.cdb.dto.ComputerPagerDTO.ORDER.DESC;
import static fr.ebiz.cdb.service.validation.ComputerValidator.DATE_FORMAT_IS_INVALID;
import static fr.ebiz.cdb.service.validation.ComputerValidator.DATE_IS_NULL;
import static fr.ebiz.cdb.service.validation.ComputerValidator.DEFAULT_LIMIT;
import static fr.ebiz.cdb.service.validation.ComputerValidator.DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE;
import static fr.ebiz.cdb.service.validation.ComputerValidator.MIN_PAGE;
import static fr.ebiz.cdb.service.validation.ComputerValidator.NAME_IS_EMPTY;
import static fr.ebiz.cdb.service.validation.ComputerValidator.NAME_IS_TOO_LONG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by bpestre on 22/02/17.
 */
public class ComputerValidatorTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    // validateName
    @Test
    public void throwExceptionWhenValidateNameWithEmptyData() throws InputValidationException {
        thrown.expect(InputValidationException.class);
        thrown.expectMessage(NAME_IS_EMPTY);
        ComputerValidator.validateName("  ");
    }

    @Test
    public void throwExceptionWhenValidateNameWithInvalidData() throws InputValidationException {
        thrown.expect(InputValidationException.class);
        thrown.expectMessage(NAME_IS_TOO_LONG);
        ComputerValidator.validateName("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz ");
    }

    @Test
    public void validateNameWithValidData() throws InputValidationException {
        ComputerValidator.validateName("valid name");
    }

    // validateDate
    @Test
    public void throwExceptionWhenValidateDateWithInvalidData() throws InputValidationException {
        thrown.expect(InputValidationException.class);
        thrown.expectMessage(DATE_FORMAT_IS_INVALID);
        ComputerValidator.validateDate("invalid date");
    }

    @Test
    public void validateDateWithValidData() throws InputValidationException {
        ComputerValidator.validateDate("2000-01-01");
    }

    // validateDiscontinuedDate
    @Test
    public void throwExceptionWhenValidateDiscontinuedDateWithInvalidDate() throws InputValidationException {
        thrown.expect(InputValidationException.class);
        thrown.expectMessage(DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE);
        ComputerValidator.validateDiscontinuedDate("2000-02-01","2000-01-01");
    }

    @Test
    public void validateDiscontinuedDateWithValidDate() throws InputValidationException {
        ComputerValidator.validateDiscontinuedDate("2000-01-01","2000-01-02");
    }

    // validateColumn
    @Test
    public void validateColumnWithNull() {
        assertEquals(NAME, ComputerValidator.validate(new ComputerPagerDTO.Builder().column(null).build()).getColumn());
    }

    @Test
    public void validateColumnWithValidData() {
        assertEquals(INTRODUCED, ComputerValidator.validate(new ComputerPagerDTO.Builder().column(INTRODUCED).build()).getColumn());
    }

    // validateOrder
    @Test
    public void validateOrderWithNull() {
        assertEquals(ASC, ComputerValidator.validate(new ComputerPagerDTO.Builder().order(null).build()).getOrder());
    }

    @Test
    public void validateOrderWithValidData() {
        assertEquals(DESC, ComputerValidator.validate(new ComputerPagerDTO.Builder().order(DESC).build()).getOrder());
    }

    // validateSearch
    @Test
    public void validateSearchWithNull() {
        assertEquals(null, ComputerValidator.validate(new ComputerPagerDTO.Builder().search(null).build()).getSearch());
    }

    @Test
    public void validateSearchWithValidData() {
        assertEquals("valid", ComputerValidator.validate(new ComputerPagerDTO.Builder().search("valid ").build()).getSearch());
    }

    // validateCurrentPage
    @Test
    public void validateCurrentPageWithZero() {
        assertEquals(MIN_PAGE, ComputerValidator.validate(new ComputerPagerDTO.Builder().currentPage(0).build()).getCurrentPage());
    }

    // validateLimit
    @Test
    public void validateLimitWithLimitTooLow() {
        assertEquals(DEFAULT_LIMIT, ComputerValidator.validate(new ComputerPagerDTO.Builder().limit(2).build()).getLimit());
    }

    @Test
    public void validateLimitWithLimitTooBig() {
        assertEquals(DEFAULT_LIMIT, ComputerValidator.validate(new ComputerPagerDTO.Builder().limit(512).build()).getLimit());
    }

    // validateCurrentPageMax
    @Test
    public void validateValidateCurrentPageMaxWithInvalidData() {
        assertEquals(4, ComputerValidator.validateCurrentPageMax(100,30,12));
    }

    //
    @Test
    public void validateComputerDTO() throws InputValidationException {
        ComputerValidator.validate(new ComputerDTO.Builder().name("name").build());
    }


}
