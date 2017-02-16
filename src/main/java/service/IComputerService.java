package main.java.service;

import main.java.model.Computer;
import main.java.service.impl.ComputerValidationException;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface IComputerService {

    List<Computer> fetchAll();
    Computer get(String id) throws ComputerValidationException;
    boolean add(Computer computer) throws ComputerValidationException;
    boolean update(Computer computer) throws ComputerValidationException;
    boolean delete(Computer computer);
    List<Computer> fetch(int limit, int offset);
    int count();
}
