package fr.ebiz.cdb.dao;

import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.model.Company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyDAO {

    /**
     * Retrieve the list of Company.
     *
     * @param connection the connection to the datasource
     * @return the list of Company
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    List<Company> fetchAll(Connection connection) throws SQLException, DAOException;

    /**
     * Retrieve the Company for the given id.
     *
     * @param id         the id of the Company
     * @param connection the connection to the datasource
     * @return the Company for the given id
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    Company fetch(int id, Connection connection) throws SQLException, DAOException;

    /**
     * Delete the company for the given id.
     *
     * @param id the id of the company
     * @param connection the connection to the datasource
     * @return true if the company is deleted
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean delete(Integer id, Connection connection) throws DAOException, SQLException;
}
