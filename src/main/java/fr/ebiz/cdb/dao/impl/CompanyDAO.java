package fr.ebiz.cdb.dao.impl;

import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.mapper.CompanyMapper;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dao.utils.DAOHelper;
import fr.ebiz.cdb.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static final String DATABASE_CONNECTION_ERROR = "Database connection error: ";
    public static final String TRANSACTION_ROLLED_BACK = "Transaction rolled back";
    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    @Override
    public List<Company> fetchAll(Connection connection) throws SQLException, DAOException {
        List<Company> list;

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            list = CompanyMapper.mapToCompanyList(resultSet);

        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return list;
    }

    @Override
    public Company fetch(int id, Connection connection) throws SQLException, DAOException {
        Company company;

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT_BY_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            company = CompanyMapper.mapToCompany(resultSet);

        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return company;
    }

}
