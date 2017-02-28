package fr.ebiz.cdb.dao;


import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Computer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public interface IComputerDAO {

    /**
     * Retrieve the computer for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     */
    Computer fetchById(int id);

    /**
     * Retrieve the computer for the given id.
     *
     * @param id         the id of the computer
     * @param connection the connection from the pool
     * @return the computer for the given id
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    Computer fetchById(int id, Connection connection) throws SQLException;

    /**
     * Retrieve the computerDTO for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     */
    ComputerDTO fetchDTOById(Integer id);

    /**
     * Add a computer.
     *
     * @param computer   the computer to add
     * @param connection the connection from the pool
     * @return <tt>true</tt> if the computer is added
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean add(Computer computer, Connection connection) throws SQLException;

    /**
     * Update a computer.
     *
     * @param computer   the computer to update
     * @param connection the connection from the pool
     * @return <tt>true</tt> if the computer is updated
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean update(Computer computer, Connection connection) throws SQLException;

    /**
     * Delete a computer.
     *
     * @param computerId the computer to delete
     * @return <tt>true</tt> if the computer is deleted
     */
    boolean delete(int computerId);

    /**
     * Retrieve a list of computerDTO with a specific limit and offset.
     *
     * @param limit      the number of computer to retrieve
     * @param offset     the index of the first computer of the list
     * @param connection the connection from the pool
     * @return the list of computerDTO with the given limit and offset
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    List<ComputerDTO> fetchPageDTO(int limit, int offset, Connection connection) throws SQLException;

    /**
     * Retrieve the number of computers.
     *
     * @param connection the connection from the pool
     * @return the number of computers
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    int count(Connection connection) throws SQLException;
}
