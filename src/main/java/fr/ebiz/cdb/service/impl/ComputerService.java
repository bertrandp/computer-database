package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.dao.DAOFactory;
import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.impl.ComputerDAO;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public enum ComputerService implements IComputerService {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
    private IComputerDAO computerDAO;

    /**
     * Computer fr.ebiz.cdb.service constructor. Fetch the instance of DAOFactory.
     */
    ComputerService() {
        this.computerDAO = ComputerDAO.INSTANCE;
    }

    @Override
    public void setComputerDAO(IComputerDAO computerDAO) {
        this.computerDAO = computerDAO;
    }

    @Override
    public ComputerDTO getDTO(Integer id) throws ComputerException, InputValidationException {
        DAOFactory daoFactory = DAOFactory.INSTANCE;
        ComputerDTO computer = null;
        try (Connection connection = daoFactory.getConnection()) {
            computer = computerDAO.fetchDTOById(id, connection);
            connection.commit();
            if (computer == null) {
                throw new ComputerException("The computer with id=" + id + " does not exists.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return computer;
    }

    @Override
    public boolean add(Computer computer) {

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {

            // Add the computer
            computerDAO.add(computer, connection);

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Computer computer) {
        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {

            // Add the computer
            computerDAO.update(computer, connection);

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(List<Integer> idList) throws InputValidationException, ComputerException {

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {
            for (Integer id : idList) {
                computerDAO.delete(id, connection);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ComputerException(e);
        }

        return true;
    }

    @Override
    public ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) {

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {

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
            e.printStackTrace();
        }

        return null;
    }

}
