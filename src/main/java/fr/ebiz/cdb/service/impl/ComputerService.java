package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.dao.ConnectionPool;
import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.impl.ComputerDAO;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static fr.ebiz.cdb.dao.impl.CompanyDAO.DATABASE_CONNECTION_ERROR;

/**
 * Created by ebiz on 14/02/17.
 */
public enum ComputerService implements IComputerService {

    INSTANCE;

    public static final String COMPUTER_NOT_FOUND = "Computer not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
    private ConnectionPool connectionPool;
    private IComputerDAO computerDAO;

    /**
     * Computer service constructor.
     */
    ComputerService() {
        this.connectionPool = ConnectionPool.INSTANCE;
        this.computerDAO = ComputerDAO.INSTANCE;
    }

    @Override
    public void setComputerDAO(IComputerDAO computerDAO) {
        this.computerDAO = computerDAO;
    }

    @Override
    public ComputerDTO getDTO(Integer id) throws DAOException {

        ComputerDTO computer;
        try (Connection connection = connectionPool.getConnection()) {
            computer = computerDAO.fetchDTOById(id, connection);
            connection.commit();

            if (computer == null) {
                throw new DAOException(COMPUTER_NOT_FOUND);
            }

            return computer;

        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }

    @Override
    public boolean add(Computer computer) throws DAOException {

        try (Connection connection = connectionPool.getConnection()) {

            // Add the computer
            computerDAO.add(computer, connection);

            // Commit the transaction
            connection.commit();

            return true;
        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Computer computer) throws DAOException {

        try (Connection connection = connectionPool.getConnection()) {

            // Add the computer
            computerDAO.update(computer, connection);

            // Commit the transaction
            connection.commit();

            return true;
        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(List<Integer> idList) throws DAOException {

        try (Connection connection = connectionPool.getConnection()) {
            for (Integer id : idList) {
                computerDAO.delete(id, connection);
            }
            connection.commit();

            return true;
        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }

    @Override
    public ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) throws DAOException {

        try (Connection connection = connectionPool.getConnection()) {

            // Count the number of computers
            page.setCount(computerDAO.count(page.getSearch(), connection));

            int pageToValidate = page.getCurrentPage();

            page.setCurrentPage(ComputerValidator.validateCurrentPageMax(page.getCount(), page.getLimit(), pageToValidate));

            // fetch computerDTO page
            int offset = (page.getCurrentPage() - 1) * page.getLimit();
            //page.setList(computerDAO.fetchPageDTO(page.getLimit(), offset, page.getSearch(), connection));
            page.setList(computerDAO.fetchPageDTO(page.getLimit(), offset, page.getSearch(), page.getOrder(), page.getColumn(), connection));

            connection.commit();

            return page;

        } catch (SQLException e) {
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }
    }

}
