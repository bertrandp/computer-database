package fr.ebiz.cdb.cli.validation;


import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerValidator {

    public static final String NAME_IS_EMPTY = "Name is empty";
    public static final String NAME_IS_TOO_LONG = "Name is too long";
    public static final String DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE = "Discontinued date is before introduced date";
    public static final String DATE_FORMAT_IS_INVALID = "Date format is invalid. Must be DD/MM/YYYY";
    public static final String DATE_IS_NULL = "Date is null";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final int MAX_LIMIT = 100;
    public static final int MIN_LIMIT = 10;
    public static final int DEFAULT_LIMIT = 50;
    public static final int MIN_PAGE = 1;
    public static final String REGEX = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
    private static final int MAX_LENGTH = 255;

    /**
     * Validate the page parameters and set them to valid values.
     *
     * @param page the ComputerPageDTO to validate
     * @return the page set with valid parameters
     */
    public static ComputerPagerDTO validate(ComputerPagerDTO page) {

        page.setLimit(validateLimit(page.getLimit()));
        page.setPage(validateCurrentPage(page.getPage()));
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
    private static int validateCurrentPage(Integer currentPage) {
        if (currentPage != null) {
            if (currentPage < MIN_PAGE) {
                currentPage = MIN_PAGE;
            }
        }
        return currentPage;
    }

    /**
     * Validate the given limit and return it, return a default value in case the input is not valid.
     *
     * @param limit the limit to validate
     * @return the validated limit
     */
    private static int validateLimit(Integer limit) {
        if (limit != null) {
            if (limit < MIN_LIMIT) {
                limit = DEFAULT_LIMIT;
            } else if (limit > MAX_LIMIT) {
                limit = DEFAULT_LIMIT;
            }
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
     * @return the list of errors
     */
    public static List<String> validate(ComputerDTO computerDTO) {
        List<String> errorList = new ArrayList<>();
        String error;
        error = validateName(computerDTO.getName());
        if (error != null) {
            errorList.add(error);
        }
        error = validateDate(computerDTO.getIntroduced());
        if (error != null) {
            errorList.add(error);
        }
        error = validateDate(computerDTO.getDiscontinued());
        if (error != null) {
            errorList.add(error);
        }
        error = validateDiscontinuedDate(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
        if (error != null) {
            errorList.add(error);
        }

        return errorList;
    }

    /**
     * Validate the given name.
     *
     * @param name the name to validate
     * @return the error message if the name is invalid else null
     */
    public static String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return NAME_IS_EMPTY;
        } else if (name.length() >= MAX_LENGTH) {
            return NAME_IS_TOO_LONG;
        }
        return null;
    }

    /**
     * Validate the given date.
     *
     * @param date the date to validate
     * @return the error message if the name is invalid else null
     */
    public static String validateDate(String date) {
        if (date != null) {
            if (!date.matches(REGEX)) {
                return DATE_FORMAT_IS_INVALID;
            }
        }
        return null;
    }

    /**
     * Validate the given discontinued date and return true if the discontinued date is after the introduced date.
     *
     * @param introduced   the introduced date to compare to
     * @param discontinued the discontinued date to validate
     * @return the error message if the name is invalid else null
     */
    public static String validateDiscontinuedDate(String introduced, String discontinued) {
        if (introduced != null && discontinued != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate introducedLD = LocalDate.parse(introduced, formatter);
            LocalDate discontinuedLD = LocalDate.parse(discontinued, formatter);
            if (introducedLD.isEqual(discontinuedLD) || introducedLD.isAfter(discontinuedLD)) {
                return DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE;
            }
        }
        return null;
    }

}
