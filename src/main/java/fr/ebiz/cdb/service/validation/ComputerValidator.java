package fr.ebiz.cdb.service.validation;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.exception.InputValidationException;

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
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String INTRODUCED_DATE_DOES_NOT_EXISTS = "Introduced date does not exists, discontinued date is not allowed";
    private static final int MAX_LENGTH = 255;

    /**
     * Validate the given name and return true if the name is valid.
     *
     * @param name the name to validate
     * @return true if the name is valid
     * @throws InputValidationException exception raised if the name is invalid
     */
    public static boolean validateName(String name) throws InputValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new InputValidationException(NAME_IS_EMPTY);
        } else if (name.length() >= MAX_LENGTH) {
            throw new InputValidationException(NAME_IS_TOO_LONG);
        }
        return true;
    }

    /**
     * Validate the given discontinued date and return true if the discontinued date is after the introduced date.
     *
     * @param discontinued the discontinued date to validate
     * @param introduced   the introduced date to compare to
     * @return true if the discontinued date is valid
     * @throws InputValidationException exception raised if the discontinued date is invalid
     */
    public static boolean validateDiscontinuedDate(LocalDate discontinued, LocalDate introduced) throws InputValidationException {
        if (introduced == null && discontinued != null) {
            throw new InputValidationException(INTRODUCED_DATE_DOES_NOT_EXISTS);
        } else if (discontinued != null) {
            if (discontinued.isEqual(introduced)) {
                throw new InputValidationException(DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE);
            } else if (discontinued.isBefore(introduced)) {
                throw new InputValidationException(DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE);
            }
        }
        return true;
    }

    /**
     * Validate the given date and return true if the date is valid.
     *
     * @param date the date to validate
     * @return true if the discontinued date is valid
     * @throws InputValidationException exception raised if the date is invalid
     */
    public static LocalDate validateInputDate(String date) throws InputValidationException {
        if (date == null) {
            throw new InputValidationException(DATE_IS_NULL);
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                throw new InputValidationException(DATE_FORMAT_IS_INVALID);
            }
        }
    }

    /**
     * Validate the given limit and return it, return a default value in case the input is not valid.
     *
     * @param limit the limit to validate
     * @return the limit
     */
    public static int validateInputLimit(Integer limit) {
        int defaultLimit = 50;
        int minimumLimit = 10;
        int maximumLimit = 100;
        if (limit != null) {
            if (limit < minimumLimit) {
                limit = defaultLimit;
            } else if (limit > maximumLimit) {
                limit = defaultLimit;
            }
        } else {
            limit = defaultLimit;
        }
        return limit;
    }

    /**
     * Validate the given page number and return it, return a default value in case the input is not valid.
     *
     * @param count the total number of entries
     * @param limit the limit value
     * @param page  the page number to validate
     * @return the page number
     */
    public static int validateInputPage(int count, int limit, Integer page) {
        int defaultPage = 1;
        int minimumPage = 1;
        int maximumPage = count / limit + 1;
        if (page != null) {
            if (page < minimumPage) {
                page = defaultPage;
            } else if (page > maximumPage) {
                page = maximumPage;
            }
        } else {
            page = defaultPage;
        }
        return page;
    }

    /**
     * Retrieve a computer set with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return the computer set with the given parameters
     * @throws InputValidationException exception raised if parameters are not valid
     */
    public static Computer validateParams(String name, String introduced, String discontinued, String companyId) throws InputValidationException {
        Computer computer = new Computer();

        if (ComputerValidator.validateName(name)) {
            computer.setName(name);
        }

        if (introduced != null && !introduced.trim().isEmpty()) {
            LocalDate introducedLD = ComputerValidator.validateInputDate(introduced);
            if (introducedLD != null) {
                computer.setIntroduced(introducedLD);
            }
        }

        if (discontinued != null && !discontinued.trim().isEmpty()) {
            LocalDate discontinuedLD = ComputerValidator.validateInputDate(discontinued);
            if (discontinuedLD != null) {
                if (computer.getIntroduced() != null) {
                    ComputerValidator.validateDiscontinuedDate(discontinuedLD, computer.getIntroduced());
                }
                computer.setDiscontinued(discontinuedLD);
            }
        }


        if (companyId != null && !companyId.trim().isEmpty()) {
            Integer id = InputValidator.validateInteger(companyId);
            if (id != null) {
                computer.setCompany(new Company(id));
            }
        }

        return computer;
    }

    /**
     * Retrieve a computer set with the given parameters.
     *
     * @param id         the id of the computer
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return the computer set with the given parameters
     * @throws InputValidationException exception raised if parameters are not valid
     */
    public static Computer validateParams(String id, String name, String introduced, String discontinued, String companyId) throws InputValidationException {
        Computer computer = validateParams(name, introduced, discontinued, companyId);
        computer.setId(InputValidator.validateInteger(id));
        return computer;
    }
}
