package fr.ebiz.cdb.ut.service;

import fr.ebiz.cdb.cli.validation.ComputerValidator;
import fr.ebiz.cdb.cli.validation.InputValidationException;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static fr.ebiz.cdb.cli.validation.ComputerValidator.DATE_FORMAT_IS_INVALID;
import static fr.ebiz.cdb.cli.validation.ComputerValidator.DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE;
import static fr.ebiz.cdb.cli.validation.ComputerValidator.NAME_IS_EMPTY;
import static fr.ebiz.cdb.cli.validation.ComputerValidator.NAME_IS_TOO_LONG;
import static fr.ebiz.cdb.model.dto.ComputerPagerDTO.COLUMN.INTRODUCED;
import static fr.ebiz.cdb.model.dto.ComputerPagerDTO.COLUMN.NAME;
import static fr.ebiz.cdb.model.dto.ComputerPagerDTO.ORDER.ASC;
import static fr.ebiz.cdb.model.dto.ComputerPagerDTO.ORDER.DESC;
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
    public void validateNameWithEmptyData() {
        assertEquals(NAME_IS_EMPTY, ComputerValidator.validateName("  "));
    }

    @Test
    public void validateNameWithInvalidData() {
        assertEquals(NAME_IS_TOO_LONG, ComputerValidator.validateName("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz "));
    }

    @Test
    public void validateNameWithValidData() {
        assertNull(ComputerValidator.validateName("valid name"));
    }

    // validateDate
    @Test
    public void validateDateWithInvalidData() {
        assertEquals(DATE_FORMAT_IS_INVALID, ComputerValidator.validateDate("invalid date"));
    }

    @Test
    public void validateDateWithValidData() {
        assertNull(ComputerValidator.validateDate("2000-01-01"));
    }

    // validateDiscontinuedDate
    @Test
    public void validateDiscontinuedDateWithInvalidDate() {
        assertEquals(DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE, ComputerValidator.validateDiscontinuedDate("2000-02-01", "2000-01-01"));
    }

    @Test
    public void validateDiscontinuedDateWithValidDate() {
        assertNull(ComputerValidator.validateDiscontinuedDate("2000-01-01", "2000-01-02"));
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
//    @Test
//    public void validateCurrentPageWithZero() {
//        assertEquals((Integer) MIN_PAGE, ComputerValidator.validate(new ComputerPagerDTO.Builder().currentPage(0).build()).getCurrentPage());
//    }
//
//    // validateLimit
//    @Test
//    public void validateLimitWithLimitTooLow() {
//        assertEquals((Integer) DEFAULT_LIMIT, ComputerValidator.validate(new ComputerPagerDTO.Builder().limit(2).build()).getLimit());
//    }
//
//    @Test
//    public void validateLimitWithLimitTooBig() {
//        assertEquals((Integer) DEFAULT_LIMIT, ComputerValidator.validate(new ComputerPagerDTO.Builder().limit(512).build()).getLimit());
//    }

    // validateCurrentPageMax
    @Test
    public void validateValidateCurrentPageMaxWithInvalidData() {
        assertEquals(4, ComputerValidator.validateCurrentPageMax(100, 30, 12));
    }

    //
//    @Test
//    public void validateComputerDTO() throws InputValidationException {
//        ComputerValidator.validate(new ComputerDTO.Builder().name("name").build());
//    }


}
