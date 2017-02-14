package service;

import model.Computer;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface IComputerService {

    List<Computer> fetchAll();
    Computer get(int id);
    boolean add(Computer computer);
    boolean update(Computer computer);
    boolean delete(Computer computer);
}
