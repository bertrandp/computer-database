package service;


import model.Computer;
import service.impl.ComputerValidationException;

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
}
