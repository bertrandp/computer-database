package fr.ebiz.cdb.service;


import fr.ebiz.cdb.dao.utils.DAOException;
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
    List<Company> fetchAll() throws DAOException;

    /**
     * Retrieve the company for the given id.
     *
     * @param companyId the id of the computer
     * @return the computer for the given id
     * @throws DAOException exception raised when id is not valid
     */
    Company fetchById(Integer companyId) throws DAOException;
}
