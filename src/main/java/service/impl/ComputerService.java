package service.impl;

import dao.DAOFactory;
import dao.IComputerDAO;
import dto.ComputerDTO;
import dto.ComputerPagerDTO;
import model.Company;
import model.Computer;
import model.ComputerPager;
import model.Pager;
import service.IComputerService;
import service.utils.ComputerValidationException;
import service.utils.ComputerValidator;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class ComputerService implements IComputerService {

    private IComputerDAO computerDAO;

    /**
     * Computer service constructor. Fetch the instance of DAOFactory.
     */
    public ComputerService() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.computerDAO = daoFactory.getComputerDAO();
    }

    @Override
    public List<ComputerDTO> fetchAllDTO() {
        return computerDAO.fetchAllDTO();
    }

    @Override
    public Computer get(String id) throws ComputerValidationException {
        if (ComputerValidator.validateId(id)) {
            return computerDAO.fetchById(Integer.valueOf(id));
        }
        return null;
    }

    @Override
    public ComputerDTO getDTO(String id) throws ComputerValidationException {
        if (ComputerValidator.validateId(id)) {
            return computerDAO.fetchDTOById(Integer.valueOf(id));
        }
        return null;
    }

    /**
     * Add the computer with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param company      the company of the computer
     * @return true if the computer is added
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    private boolean add(String name, String introduced, String discontinued, Company company) throws ComputerValidationException {
        Computer computerToAdd = setParameters(name, introduced, discontinued);
        computerToAdd.setCompany(company);
        return computerDAO.add(computerToAdd);
    }

    /**
     * Retrieve a computer set with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @return the computer set with the given parameters
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    private Computer setParameters(String name, String introduced, String discontinued) throws ComputerValidationException {
        Computer computer = new Computer();

        if (ComputerValidator.validateName(name)) {
            computer.setName(name);
        }

        if (introduced != null && !introduced.trim().isEmpty()) {
            try {
                LocalDate introducedLD = ComputerValidator.validateInputDate(introduced);
                if (introducedLD != null) {
                    computer.setIntroduced(introducedLD);
                }
            } catch (ComputerValidationException e) {
                e.addSuppressed(new ComputerValidationException("Error validating introduced date : " + introduced + "."));
                throw e;
            }
        }

        if (discontinued != null && !discontinued.trim().isEmpty()) {
            try {
                LocalDate discontinuedLD = ComputerValidator.validateInputDate(discontinued);
                if (discontinuedLD != null) {
                    if (computer.getIntroduced() != null) {
                        ComputerValidator.validateDiscontinuedDate(discontinuedLD, computer.getIntroduced());
                    }
                    computer.setDiscontinued(discontinuedLD);
                }
            } catch (ComputerValidationException e) {
                e.addSuppressed(new ComputerValidationException("Error validating discontinued date : " + discontinued + "."));
                throw e;
            }
        }
        return computer;
    }

    @Override
    public boolean addWithCompanyName(String name, String introduced, String discontinued, String companyName) throws ComputerValidationException {
        if (companyName != null && !companyName.trim().isEmpty()) {
            Company company = ComputerValidator.validateCompanyName(companyName);
            if (company != null) {
                return add(name, introduced, discontinued, company);
            }
        }
        return add(name, introduced, discontinued, null);
    }

    @Override
    public boolean addWithCompanyId(String name, String introduced, String discontinued, String companyId) throws ComputerValidationException {
        if (companyId != null && !companyId.trim().isEmpty()) {
            Company company = ComputerValidator.validateCompanyId(companyId);
            if (company != null) {
                return add(name, introduced, discontinued, company);
            }
        }
        return add(name, introduced, discontinued, null);
    }


    /**
     * Update the computer with the given parameters.
     *
     * @param id           the id of the computer
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param company      the company of the computer
     * @return true if the computer is added
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    private boolean update(String id, String name, String introduced, String discontinued, Company company) throws ComputerValidationException {
        Computer computerToAdd = setParameters(name, introduced, discontinued);
        if (ComputerValidator.validateId(id)) {
            computerToAdd.setId(Integer.valueOf(id));
        }
        computerToAdd.setCompany(company);
        return computerDAO.update(computerToAdd);
    }

    @Override
    public boolean updateWithCompanyId(String id, String name, String introduced, String discontinued, String companyId) throws ComputerValidationException {
        if (companyId != null && !companyId.trim().isEmpty()) {
            Company company = ComputerValidator.validateCompanyId(companyId);
            if (company != null) {
                return update(id, name, introduced, discontinued, company);
            }
        }
        return update(id, name, introduced, discontinued, null);
    }

    @Override
    public boolean updateWithCompanyName(String id, String name, String introduced, String discontinued, String companyName) throws ComputerValidationException {
        if (companyName != null && !companyName.trim().isEmpty()) {
            Company company = ComputerValidator.validateCompanyName(companyName);
            if (company != null) {
                return update(id, name, introduced, discontinued, company);
            }
        }
        return update(id, name, introduced, discontinued, null);
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
    public ComputerPagerDTO getPagedComputerDTOList(String inputPage, String inputLimit) throws ComputerValidationException {
        int count = computerDAO.count();
        Integer pageToValidate = null;
        Integer limitToValidate = null;
        if(inputPage != null) {
            pageToValidate = ComputerValidator.validateInputInteger(inputPage);
        }
        if(inputLimit != null) {
            limitToValidate = ComputerValidator.validateInputInteger(inputLimit);
        }
        int limit = ComputerValidator.validateInputLimit(limitToValidate);
        int page = ComputerValidator.validateInputPage(count, limit, pageToValidate);
        return new ComputerPagerDTO(count, page, limit);
    }

}
