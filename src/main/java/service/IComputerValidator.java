package service;

import model.Company;
import model.Computer;
import service.impl.ComputerValidationException;

import java.time.LocalDate;

/**
 * Created by ebiz on 16/02/17.
 */
public interface IComputerValidator {

    /**
     * Validate the given name and return true if the name is valid.
     *
     * @param name the name to validate
     * @return true if the name is valid
     * @throws ComputerValidationException exception raised if the name is invalid
     */
    boolean validateName(String name) throws ComputerValidationException;

    /**
     * Validate the given discontinued date and return true if the date is valid.
     *
     * @param discontinued the discontinued date to validate
     * @param computer     the computer to compare to
     * @return true if the discontinued date is valid
     * @throws ComputerValidationException exception raised if the discontinued date is invalid
     */
    boolean validateDiscontinuedDate(LocalDate discontinued, Computer computer) throws ComputerValidationException;

    /**
     * Validate the name of the given company and return true if the name is valid and already exists.
     *
     * @param company the company to validate
     * @return true if the company name is valid
     * @throws ComputerValidationException exception raised if the company name is invalid or the company does not exist
     */
    boolean validateCompanyName(Company company) throws ComputerValidationException;

    /**
     * Validate the given id and return true if the id is valid and a computer exists with this id.
     *
     * @param id the id of the computer
     * @return true if the id is valid and a computer with this id exists
     * @throws ComputerValidationException exception raised if the id is invalid or no computer exists with this id
     */
    boolean validateId(String id) throws ComputerValidationException;

    /**
     *
     * @param page
     * @param limit
     */
    void validatePageParam(int page, int limit) throws ComputerValidationException;
}
