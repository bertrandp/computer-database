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
 *
 *
 */
public class CompanyDAO implements ICompanyDAO {

    private DAOFactory daoFactory;
    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM company WHERE name = ?";
    private static final String ID = "id";
    private static final String NAME = "name";

    public CompanyDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {
        List<Company> list;

        try (Connection connection= daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
            ) {
            list = handleResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
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

        try (   Connection connection= daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, sql, true, object);
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {
            company = getCompany(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        }
        return company;
    }

    private Company getCompany(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            return new Company(resultSet.getInt(ID), resultSet.getString(NAME));
        }
        return null;
    }

    private List<Company> handleResultSet(ResultSet resultSet) throws SQLException {
        List<Company> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(new Company(resultSet.getInt(ID), resultSet.getString(NAME)));
        }
        return list;
    }
}
