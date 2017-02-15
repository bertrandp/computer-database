package main.java.dao;

import main.java.model.Computer;

import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public interface IComputerDAO {

    List<Computer> fetchAll();
    Computer fetchById(int id);
    void add(Computer computer);
    void update(Computer computer);
    void delete(Computer computer);

}
