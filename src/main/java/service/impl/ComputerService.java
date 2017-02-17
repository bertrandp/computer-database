package service.impl;

import dao.DAOFactory;
import dao.IComputerDAO;
import model.Computer;
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
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.fetchAll();
    }

    @Override
    public Computer get(String id) throws ComputerValidationException {
        IComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validateId(id);
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.fetchById(Integer.valueOf(id));
    }

    @Override
    public boolean add(Computer computer) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        IComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validateName(computer.getName(), computerToAdd);
        computerValidator.validateIntroducedDate(computer.getIntroduced(), computerToAdd);
        computerValidator.validateDiscontinuedDate(computer.getDiscontinued(), computerToAdd);
        computerValidator.validateCompanyName(computer.getCompany(), computerToAdd);

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.add(computerToAdd);
    }

    @Override
    public boolean update(Computer computer) throws ComputerValidationException {

        Computer computerToAdd = new Computer();
        computerToAdd.setId(computer.getId());
        IComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validateName(computer.getName(), computerToAdd);
        computerValidator.validateIntroducedDate(computer.getIntroduced(), computerToAdd);
        computerValidator.validateDiscontinuedDate(computer.getDiscontinued(), computerToAdd);
        computerValidator.validateCompanyName(computer.getCompany(), computerToAdd);

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.update(computerToAdd);
    }

    @Override
    public boolean delete(Computer computer) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.delete(computer);
    }

}
