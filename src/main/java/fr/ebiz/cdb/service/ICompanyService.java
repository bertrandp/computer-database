package fr.ebiz.cdb.service;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.InputValidationException;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyService {

    /**
     * Retrieve the list of company.
     *
     * @return the list of company
     */
    List<Company> fetchAll();

    /**
     * Retrieve the company for the given id.
     *
     * @param companyId the id of the computer
     * @return the computer for the given id
     * @throws InputValidationException exception raised when id is not valid
     * @throws CompanyException         exception raised when company is not found
     */
    Company fetchById(String companyId) throws InputValidationException, CompanyException;
}
