package fr.ebiz.cdb.dao.impl;

import fr.ebiz.cdb.dao.DAOFactory;
import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.mapper.CompanyMapper;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dao.utils.DAOHelper;
import fr.ebiz.cdb.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bpestre on 14/02/17.
 */
public enum CompanyDAO implements ICompanyDAO {

    INSTANCE;

    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM company WHERE name = ?";
    private DAOFactory daoFactory;

    /**
     * CompanyDAO constructor.
     */
    CompanyDAO() {
        this.daoFactory = DAOFactory.INSTANCE;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {
        List<Company> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = CompanyMapper.mapToCompanyList(resultSet);
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
            company = CompanyMapper.mapToCompany(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return company;
    }

}
