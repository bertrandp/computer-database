package main.java.service.impl;

import main.java.dao.DAOFactory;
import main.java.dao.IComputerDAO;
import main.java.model.Computer;
import main.java.service.IComputerService;

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
    public Computer get(String id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.fetchById(Integer.valueOf(id));
    }

    @Override
    public boolean add(Computer computer) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.add(computer);
    }

    @Override
    public boolean update(Computer computer) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.update(computer);
    }

    @Override
    public boolean delete(Computer computer) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
        return computerDAO.delete(computer);
    }
}
