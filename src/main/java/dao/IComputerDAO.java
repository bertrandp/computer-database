package main.java.dao;

import main.java.model.Computer;

import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public interface IComputerDAO {

    List<Computer> fetchAll();
    Computer fetchById(int id);
    boolean add(Computer computer);
    boolean update(Computer computer);
    boolean delete(Computer computer);
    List<Computer> fetch(int limit, int offset);
    int count();
}
