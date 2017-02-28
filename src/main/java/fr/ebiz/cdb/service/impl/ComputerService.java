package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.dao.DAOFactory;
import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.impl.CompanyDAO;
import fr.ebiz.cdb.dao.impl.ComputerDAO;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import fr.ebiz.cdb.service.validation.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static fr.ebiz.cdb.service.validation.InputValidator.validateInteger;

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
    public Computer get(String inputId) throws InputValidationException, ComputerException {
        Integer id = validateInteger(inputId);

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {
            Computer computer = computerDAO.fetchById(id, connection);
            connection.commit();
            if (computer == null) {
                throw new ComputerException("The computer with id=" + id + " does not exists.");
            }
            return computer;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ComputerException(e);
        }
    }

    @Override
    public ComputerDTO getDTO(String inputId) throws ComputerException, InputValidationException {
        Integer id = validateInteger(inputId);
        ComputerDTO computer = computerDAO.fetchDTOById(id);
        if (computer == null) {
            throw new ComputerException("The computer with id=" + id + " does not exists.");
        }
        return computer;
    }

    @Override
    public boolean add(String name, String introduced, String discontinued, String companyId) throws CompanyException, InputValidationException {
        Computer computerToAdd = ComputerValidator.validateParams(name, introduced, discontinued, companyId);

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {

            if (computerToAdd.getCompany() != null) {
                // Check if the company id is valid
                ICompanyDAO companyDAO = CompanyDAO.INSTANCE;
                companyDAO.fetch(computerToAdd.getCompany().getId(), connection);
            }

            // Add the computer
            computerDAO.add(computerToAdd, connection);

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new CompanyException(e);
        }
        return true;

    }

    @Override
    public boolean update(String id, String name, String introduced, String discontinued, String companyId) throws CompanyException, InputValidationException, ComputerException {
        Computer computerToAdd = ComputerValidator.validateParams(id, name, introduced, discontinued, companyId);

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {

            // Check if computer exists
            computerDAO.fetchById(computerToAdd.getId(), connection);

            if (computerToAdd.getCompany() != null) {
                // Check if the company exists
                ICompanyDAO companyDAO = CompanyDAO.INSTANCE;
                companyDAO.fetch(computerToAdd.getCompany().getId(), connection);
            }

            // Add the computer
            computerDAO.update(computerToAdd, connection);

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new CompanyException(e);
        }
        return true;
    }

    @Override
    public boolean delete(String[] idList) throws InputValidationException, ComputerException {

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {
            for (String inputId : idList) {
                Integer id = InputValidator.validateInteger(inputId);
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
    public ComputerPagerDTO getPagedComputerDTOList(String inputPage, String inputLimit) throws InputValidationException {

        DAOFactory daoFactory = DAOFactory.INSTANCE;
        try (Connection connection = daoFactory.getConnection()) {

            // Count the number of computers
            int count = computerDAO.count(connection);

            Integer pageToValidate = null;
            Integer limitToValidate = null;
            if (inputPage != null) {
                pageToValidate = validateInteger(inputPage);
            }
            if (inputLimit != null) {
                limitToValidate = validateInteger(inputLimit);
            }
            int limit = ComputerValidator.validateInputLimit(limitToValidate);
            int page = ComputerValidator.validateInputPage(count, limit, pageToValidate);

            ComputerPagerDTO computerPagerDTO = new ComputerPagerDTO();
            computerPagerDTO.setCurrentPage(page);
            computerPagerDTO.setCount(count);
            computerPagerDTO.setLimit(limit);

            // fetch computerDTO page
            int offset = (page - 1) * limit;
            computerPagerDTO.setList(computerDAO.fetchPageDTO(count, offset, connection));

            connection.commit();

            return computerPagerDTO;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
