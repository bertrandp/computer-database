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

    public CompanyDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {
        List<Company> list = new ArrayList<>();
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

    private List<Company> handleResultSet(ResultSet resultSet) throws SQLException {
        List<Company> list = new ArrayList<>();
        while(resultSet.next()){
            Company company = new Company();
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
            list.add(company);
        }
        return list;
    }
}
