package main.java.service;

import main.java.model.Company;
import main.java.model.Computer;
import main.java.service.impl.ComputerValidationException;

import java.sql.Date;

/**
 * Created by ebiz on 16/02/17.
 */
public interface IComputerValidator {

    void validateName(String name, Computer computer) throws ComputerValidationException;
    void validateIntroducedDate(Date introduced, Computer computer);
    void validateDiscontinuedDate(Date discontinued, Computer computer) throws ComputerValidationException;
    void validateCompanyName(Company company, Computer computer) throws ComputerValidationException;
    void validateId(String id) throws ComputerValidationException;
}
