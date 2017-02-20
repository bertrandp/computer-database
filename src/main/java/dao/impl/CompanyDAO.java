package dao.impl;

import dao.DAOFactory;
import dao.ICompanyDAO;
import dao.utils.DAOException;
import dao.utils.DAOHelper;
import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpestre on 14/02/17.
 */
public class CompanyDAO implements ICompanyDAO {

    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM company WHERE name = ?";
    private static final String ID = "id";
    private static final String NAME = "name";
    private DAOFactory daoFactory;

    /**
     * CompanyDAO constructor.
     *
     * @param daoFactory the DAOFactory
     */
    public CompanyDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {
        List<Company> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = mapResultSetToCompanyList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }

    @Override
    public Company fetch(int id) {
        return fetch(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Company fetch(String name) {
        return fetch(SQL_SELECT_BY_NAME, name);
    }

    /**
     * Retrieve the company.
     *
     * @param sql    the sql query
     * @param object the parameter
     * @return the company
     */
    private Company fetch(String sql, Object object) {
        Company company;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, sql, true, object);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            company = mapResultSetToCompany(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return company;
    }

    /**
     * Map the result set to a company.
     *
     * @param resultSet the result set to map
     * @return the company
     * @throws SQLException exception raised if the there is an issue with the database
     */
    private Company mapResultSetToCompany(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Company(resultSet.getInt(ID), resultSet.getString(NAME));
        }
        return null;
    }

    /**
     * Map the result set to a list of company.
     *
     * @param resultSet the result set to map
     * @return the list of company
     * @throws SQLException exception raised if the there is an issue with the database
     */
    private List<Company> mapResultSetToCompanyList(ResultSet resultSet) throws SQLException {
        List<Company> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Company(resultSet.getInt(ID), resultSet.getString(NAME)));
        }
        return list;
    }
}
