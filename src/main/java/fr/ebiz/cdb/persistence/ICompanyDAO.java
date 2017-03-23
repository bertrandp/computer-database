package fr.ebiz.cdb.persistence;

import fr.ebiz.cdb.model.Company;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyDAO {

    /**
     * Retrieve the list of Company.
     *
     * @return the list of Company
     */
    List<Company> fetchAll();

    /**
     * Delete the company for the given id.
     *
     * @param id the id of the company
     * @return true if the company is deleted
     */
    boolean delete(Integer id);
}
