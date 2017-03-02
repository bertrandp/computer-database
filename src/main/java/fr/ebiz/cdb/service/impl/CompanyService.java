package fr.ebiz.cdb.service.impl;


import fr.ebiz.cdb.dao.ConnectionPool;
import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.impl.CompanyDAO;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static fr.ebiz.cdb.dao.impl.CompanyDAO.DATABASE_CONNECTION_ERROR;

/**
 * Created by ebiz on 14/02/17.
 */
public enum CompanyService implements ICompanyService {

    INSTANCE;

    public static final String COMPANY_NOT_FOUND = "Company not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
    private ConnectionPool connectionPool;
    private ICompanyDAO companyDAO;

    /**
     * Company constructor. Fetch the instance of ConnectionPool.
     */
    CompanyService() {
        this.connectionPool = ConnectionPool.INSTANCE;
        this.companyDAO = CompanyDAO.INSTANCE;
    }

    @Override
    public List<Company> fetchAll() throws DAOException {
        try (Connection connection = connectionPool.getConnection()) {

            List<Company> list = companyDAO.fetchAll(connection);

            connection.commit();

            return list;

        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }

    @Override
    public Company fetchById(Integer companyId) throws DAOException {

        try (Connection connection = connectionPool.getConnection()) {

            Company company = companyDAO.fetch(companyId, connection);

            connection.commit();

            if (company == null) {
                throw new DAOException(COMPANY_NOT_FOUND);
            }

            return company;

        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }
}
