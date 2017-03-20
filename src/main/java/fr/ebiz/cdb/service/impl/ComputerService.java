package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
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
public class ComputerService implements IComputerService {


    public static final String COMPUTER_NOT_FOUND = "Computer not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

    @Autowired
    private IComputerDAO computerDAO;

    @Autowired
    private ConnectionManager connectionManager;

    @Override
    public ComputerDTO getDTO(Integer id) throws DAOException {

        Connection connection = connectionManager.getConnection();

        ComputerDTO computer;
        try {
            computer = computerDAO.fetchDTOById(id);
            if (computer == null) {
                throw new DAOException(COMPUTER_NOT_FOUND);
            }
            return computer;
        } finally {
            try {
                connectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean add(Computer computer) throws DAOException {

        Connection connection = connectionManager.getConnection();
        try {
            computerDAO.add(computer);
            return true;
        } finally {
            try {
                connectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean update(Computer computer) throws DAOException {

        Connection connection = connectionManager.getConnection();
        try {
            computerDAO.update(computer);
            return true;
        } finally {
            try {
                connectionManager.closeConnection();
            } catch (SQLException e) {
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean delete(List<Integer> idList) throws DAOException {

        Connection connection = connectionManager.getConnection();
        try {
            for (Integer id : idList) {
                computerDAO.delete(id);
            }
            return true;
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
    public ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) throws DAOException {

        Connection connection = connectionManager.getTransactionalConnection();
        try {

            page.setCount(computerDAO.count(page.getSearch()));

            int pageToValidate = page.getCurrentPage();
            page.setCurrentPage(ComputerValidator.validateCurrentPageMax(page.getCount(), page.getLimit(), pageToValidate));


            int offset = (page.getCurrentPage() - 1) * page.getLimit();
            page.setList(computerDAO.fetchPageDTO(page.getLimit(), offset, page.getSearch(), page.getOrder(), page.getColumn()));

            return page;

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
