package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.dao.DAOFactory;
import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.validation.ComputerValidator;

import java.time.LocalDate;
import java.util.List;

import static fr.ebiz.cdb.service.validation.InputValidation.validateInputInteger;

/**
 * Created by ebiz on 14/02/17.
 */
public class ComputerService implements IComputerService {

    private IComputerDAO computerDAO;

    /**
     * Computer fr.ebiz.cdb.service constructor. Fetch the instance of DAOFactory.
     */
    public ComputerService() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.computerDAO = daoFactory.getComputerDAO();
    }

    @Override
    public void setComputerDAO(IComputerDAO computerDAO) {
        this.computerDAO = computerDAO;
    }

    @Override
    public List<ComputerDTO> fetchAllDTO() {
        return computerDAO.fetchAllDTO();
    }

    @Override
    public Computer get(String inputId) throws InputValidationException, ComputerException {
        Integer id = validateInputInteger(inputId);
        Computer computer = computerDAO.fetchById(id);
        if (computer == null) {
            throw new ComputerException("The computer with id=" + id + " does not exists.");
        }
        return computer;
    }

    @Override
    public ComputerDTO getDTO(String inputId) throws ComputerException, InputValidationException {
        Integer id = validateInputInteger(inputId);
        ComputerDTO computer = computerDAO.fetchDTOById(id);
        if (computer == null) {
            throw new ComputerException("The computer with id=" + id + " does not exists.");
        }
        return computer;
    }

    /**
     * Retrieve a computer set with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return the computer set with the given parameters
     * @throws InputValidationException exception raised if parameters are not valid
     * @throws CompanyException         exception raised if company is not found
     */
    private Computer setParameters(String name, String introduced, String discontinued, String companyId) throws InputValidationException, CompanyException {
        Computer computer = new Computer();

        if (ComputerValidator.validateName(name)) {
            computer.setName(name);
        }

        if (introduced != null && !introduced.trim().isEmpty()) {
            LocalDate introducedLD = ComputerValidator.validateInputDate(introduced);
            if (introducedLD != null) {
                computer.setIntroduced(introducedLD);
            }
        }

        if (discontinued != null && !discontinued.trim().isEmpty()) {
            LocalDate discontinuedLD = ComputerValidator.validateInputDate(discontinued);
            if (discontinuedLD != null) {
                if (computer.getIntroduced() != null) {
                    ComputerValidator.validateDiscontinuedDate(discontinuedLD, computer.getIntroduced());
                }
                computer.setDiscontinued(discontinuedLD);
            }
        }

        if (companyId != null && !companyId.trim().isEmpty()) {
            CompanyService companyService = new CompanyService();
            Company company;
            company = companyService.fetchById(companyId);

            if (company != null) {
                computer.setCompany(company);
            }
        }

        return computer;
    }

    @Override
    public boolean add(String name, String introduced, String discontinued, String companyId) throws CompanyException, InputValidationException {
        Computer computerToAdd = setParameters(name, introduced, discontinued, companyId);
        return computerDAO.add(computerToAdd);
    }

    @Override
    public boolean update(String id, String name, String introduced, String discontinued, String companyId) throws CompanyException, InputValidationException, ComputerException {
        Computer computerToAdd = setParameters(name, introduced, discontinued, companyId);
        if (ComputerValidator.validateId(id, computerDAO)) {
            computerToAdd.setId(Integer.valueOf(id));
        }
        return computerDAO.update(computerToAdd);
    }


    @Override
    public boolean delete(int computerId) {
        return computerDAO.delete(computerId);
    }

    @Override
    public int count() {
        return computerDAO.count();
    }

    @Override
    public ComputerPagerDTO getPagedComputerDTOList(String inputPage, String inputLimit) throws InputValidationException {
        int count = computerDAO.count();
        Integer pageToValidate = null;
        Integer limitToValidate = null;
        if (inputPage != null) {
            pageToValidate = validateInputInteger(inputPage);
        }
        if (inputLimit != null) {
            limitToValidate = validateInputInteger(inputLimit);
        }
        int limit = ComputerValidator.validateInputLimit(limitToValidate);
        int page = ComputerValidator.validateInputPage(count, limit, pageToValidate);
        return new ComputerPagerDTO(count, page, limit);
    }

}
