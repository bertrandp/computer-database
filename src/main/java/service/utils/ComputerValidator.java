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
            throw new ComputerValidationException("Name is empty");
        } else if (name.length() >= MAX_LENGTH) {
            throw new ComputerValidationException("Name is too long");
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
        if (discontinued != null && introduced != null) {
            if (discontinued.isBefore(introduced)) {
                throw new ComputerValidationException("Discontinued date is before introduced date");
            }
        }
        return true;
    }

    /**
     * Validate the name of the given company and return true if the name is valid and already exists.
     *
     * @param company the company to validate
     * @return true if the company name is valid
     * @throws ComputerValidationException exception raised if the company name is invalid or the company does not exist
     */
    @Deprecated
    public static boolean validateCompanyName(Company company) throws ComputerValidationException {
        if (company != null) {
            if (company.getName() == null || company.getName().trim().isEmpty()) {
                return true;
            } else if (company.getName().length() >= MAX_LENGTH) {
                throw new ComputerValidationException("Company name is too long");
            } else {
                ICompanyService companyService = new CompanyService();
                if (!companyService.nameAlreadyExists(company.getName())) {
                    throw new ComputerValidationException("Company name doesn't exists");
                }
            }
        }
        return true;
    }

    /**
     * Validate the given id and return true if the id is valid and a computer exists with this id.
     *
     * @param id the id of the computer
     * @return true if the id is valid and a computer with this id exists
     * @throws ComputerValidationException exception raised if the id is invalid or no computer exists with this id
     */
    @Deprecated
    public static boolean validateId(String id) throws ComputerValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new ComputerValidationException("Id is empty");
        } else {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(id);
            if (m.find()) {
                throw new ComputerValidationException("Id is not a valid number");
            }
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.getComputerDAO();

            if (computerDAO.fetchById(Integer.valueOf(id)) == null) {
                throw new ComputerValidationException("Id " + id + " does not exist");
            }
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
    public static void validatePageParam(int page, int limit) throws ComputerValidationException {

        if (limit < 1) {
            throw new ComputerValidationException("Page size is below 1");
        } else if (limit > 100) {
            throw new ComputerValidationException("Page size is too large (above 100)");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();

        if (page < 1) {
            throw new ComputerValidationException("Page number is below 1");
        } else if (page > computerDAO.count() / limit + 1) {
            throw new ComputerValidationException("Page number is too large");
        }

    }

    /**
     * Validate the given date and return true if the date is valid.
     *
     * @param date the date to validate
     * @return true if the discontinued date is valid
     * @throws ComputerValidationException exception raised if the date is invalid
     */
    public static LocalDate validateDate(String date) throws ComputerValidationException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ComputerValidationException("Date format is invalid. Must be DD/MM/YYYY");
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
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(companyId);
        if (m.find()) {
            throw new ComputerValidationException("Company Id is not a valid number");
        } else {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ICompanyDAO companyDAO = daoFactory.getCompanyDAO();
            Company company = companyDAO.fetch(Integer.valueOf(companyId));
            if (company != null) {
                return company;
            }
        }
        return null;
    }
}
