package service.impl;

import dao.DAOFactory;
import dao.IComputerDAO;
import model.Computer;
import model.ComputerPager;
import model.Pager;
import service.IComputerService;
import service.IComputerValidator;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class ComputerService implements IComputerService {

    @Override
    public List<Computer> fetchAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        return computerDAO.fetchAll();
    }

    @Override
    public Computer get(String id) throws ComputerValidationException {
        IComputerValidator computerValidator = new ComputerValidator();
        if (computerValidator.validateId(id)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.getComputerDAO();
            return computerDAO.fetchById(Integer.valueOf(id));
        }
        return null;
    }

    @Override
    public boolean add(Computer computer) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        IComputerValidator computerValidator = new ComputerValidator();
        if (computerValidator.validateName(computer.getName())) {
            computerToAdd.setName(computer.getName());
        }
        computerToAdd.setIntroduced(computer.getIntroduced());
        if (computerValidator.validateDiscontinuedDate(computer.getDiscontinued(), computerToAdd)) {
            computerToAdd.setDiscontinued(computer.getDiscontinued());
        }
        if (computerValidator.validateCompanyName(computer.getCompany())) {
            computerToAdd.setCompany(computer.getCompany());
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        return computerDAO.add(computerToAdd);
    }

    @Override
    public boolean update(Computer computer) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        computerToAdd.setId(computer.getId());
        IComputerValidator computerValidator = new ComputerValidator();
        if (computerValidator.validateName(computer.getName())) {
            computerToAdd.setName(computer.getName());
        }
        computerToAdd.setIntroduced(computer.getIntroduced());
        if (computerValidator.validateDiscontinuedDate(computer.getDiscontinued(), computerToAdd)) {
            computerToAdd.setDiscontinued(computer.getDiscontinued());
        }
        if (computerValidator.validateCompanyName(computer.getCompany())) {
            computerToAdd.setCompany(computer.getCompany());
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        return computerDAO.update(computerToAdd);
    }

    @Override
    public boolean delete(Computer computer) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        return computerDAO.delete(computer);
    }

    @Override
    public int count() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        return computerDAO.count();
    }

    @Override
    public Pager getPagedComputerList() {
        return new ComputerPager();
    }

    @Override
    public Pager getPagedComputerList(int page, int limit) throws ComputerValidationException {
        IComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validatePageParam(page, limit);
        return new ComputerPager(page, limit);
    }

}
