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
     * @param connection
     * @return the list of Company
     */
    List<Company> fetchAll(Connection connection) throws SQLException, DAOException;

    /**
     * Retrieve the Company for the given id.
     *
     * @param id         the id of the Company
     * @param connection
     * @return the Company for the given id
     */
    Company fetch(int id, Connection connection) throws SQLException, DAOException;

}
