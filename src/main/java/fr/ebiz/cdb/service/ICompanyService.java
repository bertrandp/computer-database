package fr.ebiz.cdb.service;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.utils.DAOException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyService {

    /**
     * Retrieve the list of company.
     *
     * @return the list of company
     * @throws DAOException exception raised if there is an error with DAO
     */
    List<Company> fetchAll() throws DAOException;

    /**
     * Retrieve the company for the given id.
     *
     * @param companyId the id of the computer
     * @return the computer for the given id
     * @throws DAOException exception raised if there is an error with DAO
     */
    Company fetchById(Integer companyId) throws DAOException;

    /**
     * Delete the company for the given id and delete the computers linked to this company.
     *
     * @param id the id of the company
     * @return true if the company is deleted
     * @throws SQLException exception raised if there is an error with datasource
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean delete(Integer id) throws DAOException, SQLException;
}
