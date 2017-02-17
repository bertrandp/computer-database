package service;

import model.Company;
import model.Computer;
import service.impl.ComputerValidationException;

import java.time.LocalDate;

/**
 * Created by ebiz on 16/02/17.
 */
public interface IComputerValidator {

    void validateName(String name, Computer computer) throws ComputerValidationException;
    void validateIntroducedDate(LocalDate introduced, Computer computer);
    void validateDiscontinuedDate(LocalDate discontinued, Computer computer) throws ComputerValidationException;
    void validateCompanyName(Company company, Computer computer) throws ComputerValidationException;
    void validateId(String id) throws ComputerValidationException;
}
