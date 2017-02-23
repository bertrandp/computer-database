package service.utils;


import dao.DAOFactory;
import dao.ICompanyDAO;
import dao.IComputerDAO;
import model.Company;
import service.ICompanyService;
import service.impl.CompanyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerValidator {

    public static final String NAME_IS_EMPTY = "Name is empty";
    public static final String NAME_IS_TOO_LONG = "Name is too long";
    public static final String DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE = "Discontinued date is before introduced date";
    public static final String DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE = "Discontinued date is same as introduced date";
    public static final String COMPANY_NAME_IS_TOO_LONG = "Company name is too long";
    public static final String COMPANY_NAME_DOES_NOT_EXISTS = "Company name does not exists";
    public static final String ID_IS_EMPTY = "Id is empty";
    public static final String ID_IS_NOT_A_VALID_NUMBER = "Id is not a valid number";
    public static final String PAGE_SIZE_IS_BELOW_1 = "Page size is below 1";
    public static final String PAGE_SIZE_IS_TOO_LARGE = "Page size is too large";
    public static final String PAGE_NUMBER_IS_BELOW_1 = "Page number is below 1";
    public static final String PAGE_NUMBER_IS_TOO_LARGE = "Page number is too large";
    public static final String DATE_FORMAT_IS_INVALID = "Date format is invalid. Must be DD/MM/YYYY";
    public static final String COMPANY_ID_IS_NOT_A_VALID_NUMBER = "Company Id is not a valid number";
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
     * @throws ComputerValidationException exception raised if the name is invalid
     */
    public static boolean validateName(String name) throws ComputerValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ComputerValidationException(NAME_IS_EMPTY);
        } else if (name.length() >= MAX_LENGTH) {
            throw new ComputerValidationException(NAME_IS_TOO_LONG);
        }
        return true;
    }

    /**
     * Validate the given discontinued date and return true if the discontinued date is after the introduced date.
     *
     * @param discontinued the discontinued date to validate
     * @param introduced   the introduced date to compare to
     * @return true if the discontinued date is valid
     * @throws ComputerValidationException exception raised if the discontinued date is invalid
     */
    public static boolean validateDiscontinuedDate(LocalDate discontinued, LocalDate introduced) throws ComputerValidationException {
        if (introduced == null && discontinued != null) {
            throw new ComputerValidationException(INTRODUCED_DATE_DOES_NOT_EXISTS);
        } else if (discontinued != null) {
            if (discontinued.isEqual(introduced)) {
                throw new ComputerValidationException(DISCONTINUED_DATE_IS_SAME_AS_INTRODUCED_DATE);
            } else if (discontinued.isBefore(introduced)) {
                throw new ComputerValidationException(DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE);
            }
        }
        return true;
    }

    /**
     * Validate the name of the given company and return true if the name is valid and already exists.
     *
     * @param companyName the company to validate
     * @return true if the company name is valid
     * @throws ComputerValidationException exception raised if the company name is invalid or the company does not exist
     */
    public static Company validateCompanyName(String companyName) throws ComputerValidationException {
        if (companyName == null || companyName.trim().isEmpty()) {
            return null;
        } else if (companyName.length() >= MAX_LENGTH) {
            throw new ComputerValidationException(COMPANY_NAME_IS_TOO_LONG);
        } else {
            ICompanyService companyService = new CompanyService();
            Company company = companyService.fetch(companyName);
            if (company == null) {
                throw new ComputerValidationException(COMPANY_NAME_DOES_NOT_EXISTS);
            } else {
                return company;
            }
        }
    }

    /**
     * Validate the given id and return true if the id is valid and a computer exists with this id.
     *
     * @param inputId the id of the computer
     * @return true if the id is valid and a computer with this id exists
     * @throws ComputerValidationException exception raised if the id is invalid or no computer exists with this id
     */
    public static boolean validateId(String inputId) throws ComputerValidationException {
        Integer id = validateInputInteger(inputId);
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        if (computerDAO.fetchById(Integer.valueOf(id)) == null) {
            throw new ComputerValidationException("Id " + id + " does not exist");
        }
        return true;
    }

    /**
     * Validate the parameters for pagination.
     *
     * @param page  the page index to validate
     * @param limit the number of item to validate
     * @throws ComputerValidationException exception raised if the parameters are invalid
     */
    @Deprecated
    public static void validatePageParam(int page, int limit) throws ComputerValidationException {
        if (limit < 1) {
            throw new ComputerValidationException(PAGE_SIZE_IS_BELOW_1);
        } else if (limit > 100) {
            throw new ComputerValidationException(PAGE_SIZE_IS_TOO_LARGE);
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        if (page < 1) {
            throw new ComputerValidationException(PAGE_NUMBER_IS_BELOW_1);
        } else if (page > computerDAO.count() / limit + 1) {
            throw new ComputerValidationException(PAGE_NUMBER_IS_TOO_LARGE);
        }
    }

    /**
     * Validate the given date and return true if the date is valid.
     *
     * @param date the date to validate
     * @return true if the discontinued date is valid
     * @throws ComputerValidationException exception raised if the date is invalid
     */
    public static LocalDate validateInputDate(String date) throws ComputerValidationException {
        if (date == null) {
            throw new ComputerValidationException(DATE_IS_NULL);
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                throw new ComputerValidationException(DATE_FORMAT_IS_INVALID);
            }
        }
    }

    /**
     * Validate the given company id and return the company if the id valid. Return null otherwise.
     *
     * @param companyId the company id to validate
     * @return the company if the id valid, return null otherwise
     * @throws ComputerValidationException exception raised if the company name is invalid or the company does not exist
     */
    public static Company validateCompanyId(String companyId) throws ComputerValidationException {
        Integer id = validateInputInteger(companyId);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ICompanyDAO companyDAO = daoFactory.getCompanyDAO();
        Company company = companyDAO.fetch(Integer.valueOf(id));
        if (company != null) {
            return company;
        }
        return null;
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
        if(limit != null) {
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
     * @param page the page number to validate
     * @return the page number
     */
    public static int validateInputPage(int count, int limit, Integer page) {
        int defaultPage = 1;
        int minimumPage = 1;
        int maximumPage = count / limit + 1;
        if(page != null) {
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

    public static Integer validateInputInteger(String input) throws ComputerValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new ComputerValidationException(ID_IS_EMPTY);
        } else {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(input);
            if (m.find()) {
                throw new ComputerValidationException(ID_IS_NOT_A_VALID_NUMBER);
            }
        }
        return Integer.valueOf(input);
    }
}
