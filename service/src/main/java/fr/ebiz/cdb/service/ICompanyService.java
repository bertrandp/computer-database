package fr.ebiz.cdb.service;


import fr.ebiz.cdb.core.Company;

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
     * Delete the company for the given id and delete the computers linked to this company.
     *
     * @param id the id of the company
     * @return true if the company is deleted
     */
    boolean delete(Integer id);
}
