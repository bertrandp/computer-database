package fr.ebiz.cdb.service.impl;


import fr.ebiz.cdb.dao.ConnectionManager;
import fr.ebiz.cdb.dao.ConnectionPool;
import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.impl.CompanyDAO;
import fr.ebiz.cdb.dao.impl.ComputerDAO;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static fr.ebiz.cdb.dao.impl.CompanyDAO.DATABASE_CONNECTION_ERROR;
import static fr.ebiz.cdb.dao.impl.CompanyDAO.TRANSACTION_ROLLED_BACK;

/**
 * Created by ebiz on 14/02/17.
 */
public enum CompanyService implements ICompanyService {

    INSTANCE;

    public static final String COMPANY_NOT_FOUND = "Company not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
    private ICompanyDAO companyDAO;

    /**
     * Company constructor. Fetch the instance of ConnectionPool.
     */
    CompanyService() {
        this.companyDAO = CompanyDAO.INSTANCE;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {

        Connection connection = ConnectionManager.getConnection();

        try  {
            List<Company> list = companyDAO.fetchAll();
            connection.commit();

            return list;

        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        } finally {
            try {
                ConnectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    public Company fetchById(Integer companyId) throws DAOException {

        Connection connection = ConnectionManager.getConnection();

        try {
            Company company = companyDAO.fetch(companyId);
            connection.commit();

            if (company == null) {
                throw new DAOException(COMPANY_NOT_FOUND);
            }

            return company;

        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        } finally {
            try {
                ConnectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, DAOException {

        Connection connection = ConnectionManager.getConnection();

        try {

            // delete computers by company id
            IComputerDAO computerDAO = ComputerDAO.INSTANCE;
            computerDAO.deleteByCompanyId(id);

            // delete company
            companyDAO.delete(id);

            connection.commit();

            return true;
        } catch (DAOException | SQLException e) {
            connection.rollback();
            throw new DAOException(TRANSACTION_ROLLED_BACK, e);
        } finally {
            ConnectionManager.closeConnection();
        }
    }
}
