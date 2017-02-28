package fr.ebiz.cdb.dao;

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
     * @return the list of Company
     */
    List<Company> fetchAll();

    /**
     * Retrieve the Company for the given id.
     *
     * @param id the id of the Company
     * @return the Company for the given id
     */
    Company fetch(int id);

    /**
     * Retrieve the Company for the given id.
     *
     * @param id the id of the Company
     * @param connection the connection from the pool
     * @return the Company for the given id
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    Company fetch(int id, Connection connection) throws SQLException;

    /**
     * Retrieve the Company for the given name.
     *
     * @param name the name of the Company
     * @return the Company for the given name
     */
    Company fetch(String name);

}
