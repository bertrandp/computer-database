package dao;


import model.Computer;

import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public interface IComputerDAO {

    /**
     * Retrieve the list of computer
     * @return the list of computer
     */
    List<Computer> fetchAll();

    /**
     * Retrieve the computer for the given id
     * @param id    the id of the computer
     * @return the computer for the given id
     */
    Computer fetchById(int id);

    /**
     * Add a computer
     * @param computer  the computer to add
     * @return  <tt>true</tt> if the computer is added
     */
    boolean add(Computer computer);

    /**
     * Update a computer
     * @param computer  the computer to update
     * @return  <tt>true</tt> if the computer is updated
     */
    boolean update(Computer computer);

    /**
     * Delete a computer
     * @param computer  the computer to delete
     * @return  <tt>true</tt> if the computer is deleted
     */
    boolean delete(Computer computer);

    /**
     * Retrieve a list of computer with a specific limit and offset
     * @param limit     the number of computer to retrieve
     * @param offset    the index of the first computer of the list
     * @return  the list of computer with the given limit and offset
     */
    List<Computer> fetch(int limit, int offset);

    /**
     * Retrieve the number of computers
     * @return the number of computers
     */
    int count();
}
