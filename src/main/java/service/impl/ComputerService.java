package service.impl;

import dao.DAOFactory;
import dao.IComputerDAO;
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

    private DAOFactory daoFactory;
    private IComputerDAO computerDAO;

    /**
     * Computer service constructor. Fetch the instance of DAOFactory.
     */
    public ComputerService() {
        this.daoFactory = DAOFactory.getInstance();
        this.computerDAO = daoFactory.getComputerDAO();
    }

    @Override
    public List<Computer> fetchAll() {
        return computerDAO.fetchAll();
    }

    @Override
    public Computer get(String id) throws ComputerValidationException {
        ComputerValidator computerValidator = new ComputerValidator();
        if (ComputerValidator.validateId(id)) {
            return computerDAO.fetchById(Integer.valueOf(id));
        }
        return null;
    }

    @Deprecated
    @Override
    public boolean add(Computer computer) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        if (ComputerValidator.validateName(computer.getName())) {
            computerToAdd.setName(computer.getName());
        }
        computerToAdd.setIntroduced(computer.getIntroduced());
        if (ComputerValidator.validateDiscontinuedDate(computer.getDiscontinued(), computerToAdd.getIntroduced())) {
            computerToAdd.setDiscontinued(computer.getDiscontinued());
        }
        if (ComputerValidator.validateCompanyName(computer.getCompany())) {
            computerToAdd.setCompany(computer.getCompany());
        }

        return computerDAO.add(computerToAdd);
    }

    @Override
    public boolean add(String name, String introduced, String discontinued, String companyId) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        if (ComputerValidator.validateName(name)) {
            computerToAdd.setName(name);
        }

        if (introduced != null && !introduced.trim().isEmpty()) {
            try {
                LocalDate introducedLD = ComputerValidator.validateDate(introduced);
                if (introducedLD != null) {
                    computerToAdd.setIntroduced(introducedLD);
                }
            } catch (ComputerValidationException e) {
                e.addSuppressed(new ComputerValidationException("Error validating introduced date : " + introduced + "."));
                throw e;
            }
        }

        if (discontinued != null && !discontinued.trim().isEmpty()) {
            try {
                LocalDate discontinuedLD = ComputerValidator.validateDate(discontinued);
                if (discontinuedLD != null) {
                    if (computerToAdd.getIntroduced() != null) {
                        ComputerValidator.validateDiscontinuedDate(discontinuedLD, computerToAdd.getIntroduced());
                    }
                    computerToAdd.setDiscontinued(discontinuedLD);
                }
            } catch (ComputerValidationException e) {
                e.addSuppressed(new ComputerValidationException("Error validating discontinued date : " + discontinued + "."));
                throw e;
            }
        }

        if (companyId != null || !companyId.trim().isEmpty()) {
            Company company = ComputerValidator.validateCompanyId(companyId);
            if (company != null) {
                computerToAdd.setCompany(company);
            }
        }

        return computerDAO.add(computerToAdd);

    }

    @Override
    public boolean update(Computer computer) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        computerToAdd.setId(computer.getId());
        if (ComputerValidator.validateName(computer.getName())) {
            computerToAdd.setName(computer.getName());
        }
        computerToAdd.setIntroduced(computer.getIntroduced());
        if (ComputerValidator.validateDiscontinuedDate(computer.getDiscontinued(), computerToAdd.getIntroduced())) {
            computerToAdd.setDiscontinued(computer.getDiscontinued());
        }
        if (ComputerValidator.validateCompanyName(computer.getCompany())) {
            computerToAdd.setCompany(computer.getCompany());
        }

        return computerDAO.update(computerToAdd);
    }

    @Override
    public boolean delete(Computer computer) {
        return computerDAO.delete(computer);
    }

    @Override
    public int count() {
        return computerDAO.count();
    }

    @Override
    public Pager getPagedComputerList() {
        return new ComputerPager();
    }

    @Override
    public Pager getPagedComputerList(int page, int limit) throws ComputerValidationException {
        ComputerValidator.validatePageParam(page, limit);
        return new ComputerPager(page, limit);
    }

}
