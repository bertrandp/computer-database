package fr.ebiz.cdb.service;


import fr.ebiz.cdb.model.Company;

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
     */
    Company fetchById(Integer companyId);

    /**
     * Delete the company for the given id and delete the computers linked to this company.
     *
     * @param id the id of the company
     * @return true if the company is deleted
     */
    boolean delete(Integer id);
}
