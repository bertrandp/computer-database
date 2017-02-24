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
     * Check if company already exists for the given name.
     *
     * @param name the name of the company
     * @return true if the company already exists
     */
    Company fetch(String name);

    /**
     * Check if company already exists for the given id.
     *
     * @param id the name of the company
     * @return true if the company already exists
     */
    Company fetch(int id);

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
