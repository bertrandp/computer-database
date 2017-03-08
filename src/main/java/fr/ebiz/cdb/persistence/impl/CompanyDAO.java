package fr.ebiz.cdb.persistence.impl;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.ICompanyDAO;
import fr.ebiz.cdb.persistence.mapper.CompanyMapper;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.persistence.utils.DAOHelper;
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
    public static final String ERROR_DELETING_COMPANY = "Error deleting company";
    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM company WHERE id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    @Override
    public List<Company> fetchAll() throws DAOException {
        List<Company> list;

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(ConnectionManager.getConnection(), SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            list = CompanyMapper.mapToCompanyList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return list;
    }

    @Override
    public Company fetch(int id) throws DAOException {
        Company company;

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(ConnectionManager.getConnection(), SQL_SELECT_BY_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            company = CompanyMapper.mapToCompany(resultSet);
        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return company;
    }

    @Override
    public boolean delete(Integer id) throws DAOException, SQLException {

        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_DELETE, true, id)
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException(ERROR_DELETING_COMPANY);
            }
        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return true;
    }
}
