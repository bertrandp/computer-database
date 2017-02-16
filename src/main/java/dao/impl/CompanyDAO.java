package main.java.dao.impl;

import main.java.dao.DAOException;
import main.java.dao.utils.DAOHelper;
import main.java.model.Company;
import main.java.dao.DAOFactory;
import main.java.dao.ICompanyDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class CompanyDAO implements ICompanyDAO{

    private DAOFactory daoFactory;
    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM company WHERE name = ?";

    public CompanyDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {
        List<Company> list;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT, true);
            resultSet = preparedStatement.executeQuery();
            list = handleResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection(resultSet, preparedStatement, connection);
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

    private Company fetch(String sql, Object object) {
        Company company;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, sql, true, object);
            resultSet = preparedStatement.executeQuery();
            company = getCompany(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection(resultSet, preparedStatement, connection);
        }
        return company;
    }

    private Company getCompany(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            return new Company(resultSet.getInt("id"), resultSet.getString("name"));
        }
        return null;
    }

    private List<Company> handleResultSet(ResultSet resultSet) throws SQLException {
        List<Company> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(new Company(resultSet.getInt("id"), resultSet.getString("name")));
        }
        return list;
    }
}
