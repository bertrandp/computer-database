package fr.ebiz.cdb.service.validation;


import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerValidator {

    public static final String NAME_IS_EMPTY = "Name is empty";
    public static final String NAME_IS_TOO_LONG = "Name is too long";
    public static final String DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE = "Discontinued date is before introduced date";
    public static final String DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE = "Discontinued date is same as introduced date";
    public static final String ID_IS_EMPTY = "Id is empty";
    public static final String ID_IS_NOT_A_VALID_NUMBER = "Id is not a valid number";
    public static final String DATE_FORMAT_IS_INVALID = "Date format is invalid. Must be DD/MM/YYYY";
    public static final String DATE_IS_NULL = "Date is null";
    public static final String ID_IS_NULL = "Id is null";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String INTRODUCED_DATE_DOES_NOT_EXISTS = "Introduced date does not exists, discontinued date is not allowed";
    private static final int MAX_LENGTH = 255;

    /**
     * Validate the page parameters and set them to valid values.
     *
     * @param page the ComputerPageDTO to validate
     * @return the page set with valid parameters
     */
    public static ComputerPagerDTO validate(ComputerPagerDTO page) {

        page.setLimit(validateLimit(page.getLimit()));
        page.setCurrentPage(validateCurrentPage(page.getCurrentPage()));
        page.setSearch(validateSearch(page.getSearch()));
        page.setOrder(validateOrder(page.getOrder()));
        page.setColumn(validateColumn(page.getColumn()));

        return page;
    }

    /**
     * Validate the given column.
     *
     * @param column the column to validate
     * @return the validated column
     */
    private static ComputerPagerDTO.COLUMN validateColumn(ComputerPagerDTO.COLUMN column) {
        if (column == null) {
            return ComputerPagerDTO.COLUMN.NAME;
        } else {
            return column;
        }
    }

    /**
     * Validate the given order.
     *
     * @param order the order to validate
     * @return the validated order
     */
    private static ComputerPagerDTO.ORDER validateOrder(ComputerPagerDTO.ORDER order) {
        if (order == null) {
            return ComputerPagerDTO.ORDER.ASC;
        } else {
            return order;
        }
    }

    /**
     * Validate the given search.
     *
     * @param search the order to validate
     * @return the validated search
     */
    private static String validateSearch(String search) {
        if (search != null && !search.trim().isEmpty()) {
            return search.trim();
        } else {
            return null;
        }
    }

    /**
     * Validate the given page index, return a default value in case the input is not valid.
     *
     * @param currentPage the page index to validate
     * @return the validated page index
     */
    private static int validateCurrentPage(int currentPage) {
        int defaultPage = 1;
        int minimumPage = 1;
        if (currentPage < minimumPage) {
            currentPage = defaultPage;
        }
        return currentPage;
    }

    /**
     * Validate the given limit and return it, return a default value in case the input is not valid.
     *
     * @param limit the limit to validate
     * @return the validated limit
     */
    private static int validateLimit(int limit) {
        int defaultLimit = 50;
        int minimumLimit = 10;
        int maximumLimit = 100;
        if (limit < minimumLimit) {
            limit = defaultLimit;
        } else if (limit > maximumLimit) {
            limit = defaultLimit;
        }
        return limit;
    }

    /**
     * Validate the given page number and return it, return a default value in case the input is not valid.
     *
     * @param count       the total number of entries
     * @param limit       the limit value
     * @param currentPage the page number to validate
     * @return the validated page number
     */
    public static int validateCurrentPageMax(int count, int limit, int currentPage) {
        int maximumPage = count / limit + 1;
        if (currentPage > maximumPage) {
            currentPage = maximumPage;
        }
        return currentPage;
    }


    /**
     * Validate the computerDTO parameters.
     *
     * @param computerDTO the computerDTO to validate
     * @throws InputValidationException exception raised if a parameter is invalid
     */
    public static void validate(ComputerDTO computerDTO) throws InputValidationException {
        validateName(computerDTO.getName());
        validateDate(computerDTO.getIntroduced());
        validateDate(computerDTO.getDiscontinued());
        validateDiscontinuedDateIsGreaterThanIntroduced(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
    }

    /**
     * Validate the given name.
     *
     * @param name the name to validate
     * @throws InputValidationException exception raised if the name is invalid
     */
    public static void validateName(String name) throws InputValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new InputValidationException(NAME_IS_EMPTY);
        } else if (name.length() >= MAX_LENGTH) {
            throw new InputValidationException(NAME_IS_TOO_LONG);
        }
    }

    /**
     * Validate the given date.
     *
     * @param date the date to validate
     * @throws InputValidationException exception raised if the date is invalid
     */
    public static void validateDate(String date) throws InputValidationException {
        if (date != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                throw new InputValidationException(DATE_FORMAT_IS_INVALID);
            }
        }
    }

    /**
     * Validate the given discontinued date and return true if the discontinued date is after the introduced date.
     *
     * @param discontinued the discontinued date to validate
     * @param introduced   the introduced date to compare to
     * @throws InputValidationException exception raised if the discontinued date is invalid
     */
    private static void validateDiscontinuedDateIsGreaterThanIntroduced(String introduced, String discontinued) throws InputValidationException {
        if (introduced != null && discontinued != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate introducedLD = LocalDate.parse(introduced, formatter);
            LocalDate discontinuedLD = LocalDate.parse(discontinued, formatter);
            if (introducedLD.isEqual(discontinuedLD)) {
                throw new InputValidationException(DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE);
            } else if (introducedLD.isAfter(discontinuedLD)) {
                throw new InputValidationException(DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE);
            }
        }
    }

}
