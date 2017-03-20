package fr.ebiz.cdb.service.impl;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.ICompanyDAO;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static fr.ebiz.cdb.persistence.impl.CompanyDAO.DATABASE_CONNECTION_ERROR;
import static fr.ebiz.cdb.persistence.impl.CompanyDAO.TRANSACTION_ROLLED_BACK;

/**
 * Created by ebiz on 14/02/17.
 */
@Service
public class CompanyService implements ICompanyService {


    public static final String COMPANY_NOT_FOUND = "Company not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IComputerDAO computerDAO;

    @Autowired
    private ConnectionManager connectionManager;


    @Override
    public List<Company> fetchAll() throws DAOException {

        Connection connection = connectionManager.getConnection();

        try {
            List<Company> list = companyDAO.fetchAll();
            return list;
        } finally {
            try {
                connectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    public Company fetchById(Integer companyId) throws DAOException {

        Connection connection = connectionManager.getConnection();

        try {
            Company company = companyDAO.fetch(companyId);
            if (company == null) {
                throw new DAOException(COMPANY_NOT_FOUND);
            }
            return company;
        } finally {
            try {
                connectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id) throws DAOException {

        Connection connection = connectionManager.getTransactionalConnection();

        try {
            computerDAO.deleteByCompanyId(id);
            companyDAO.delete(id);
            return true;
        } catch (DAOException e) {
            throw new DAOException(TRANSACTION_ROLLED_BACK, e);
        } finally {
            try {
                connectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }

    }
}
