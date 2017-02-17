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
     * Validate the given name and add it to the given computer if the name is valid
     * @param name the name to valid
     * @throws ComputerValidationException
     */
    boolean validateName(String name) throws ComputerValidationException;

    boolean validateDiscontinuedDate(LocalDate discontinued, Computer computer) throws ComputerValidationException;

    boolean validateCompanyName(Company company) throws ComputerValidationException;

    boolean validateId(String id) throws ComputerValidationException;
}
