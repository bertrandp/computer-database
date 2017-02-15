package main.java.dao.impl;

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

    public static PreparedStatement initPreparedStatement( Connection connection, String sql, boolean returnGeneratedKeys, Object... objects ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objects.length; i++ ) {
            preparedStatement.setObject( i + 1, objects[i] );
        }
        return preparedStatement;
    }

    @Override
    public List<Company> fetchAll() {
        List<Company> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, SQL_SELECT, true);
            resultSet = preparedStatement.executeQuery();
            list = handleResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return list;
    }

    private List<Company> handleResultSet(ResultSet resultSet) {
        List<Company> list = new ArrayList<>();

        try {
            while(resultSet.next()){
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                list.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
