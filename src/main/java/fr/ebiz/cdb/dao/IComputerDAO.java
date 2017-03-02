package fr.ebiz.cdb.dao;


import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
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
     * @param id         the id of the computer
     * @param connection the connection from the pool
     * @return the computer for the given id
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    Computer fetchById(int id, Connection connection) throws SQLException, DAOException;

    /**
     * Retrieve the computerDTO for the given id.
     *
     * @param id         the id of the computer
     * @param connection
     * @return the computer for the given id
     */
    ComputerDTO fetchDTOById(Integer id, Connection connection) throws SQLException, DAOException;

    /**
     * Add a computer.
     *
     * @param computer   the computer to add
     * @param connection the connection from the pool
     * @return <tt>true</tt> if the computer is added
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean add(Computer computer, Connection connection) throws SQLException, DAOException;

    /**
     * Update a computer.
     *
     * @param computer   the computer to update
     * @param connection the connection from the pool
     * @return <tt>true</tt> if the computer is updated
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean update(Computer computer, Connection connection) throws SQLException, DAOException;

    /**
     * Delete a computer.
     *
     * @param computerId the computer to delete
     * @param connection the connection from the pool
     * @return <tt>true</tt> if the computer is deleted
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean delete(int computerId, Connection connection) throws SQLException, DAOException;

    /**
     * Retrieve a list of computerDTO with a specific limit and offset.
     *
     * @param limit      the number of computer to retrieve
     * @param offset     the index of the first computer of the list
     * @param search     the filter for the search
     * @param order
     * @param column
     * @param connection the connection from the pool  @return the list of computerDTO with the given limit and offset  @return the list of computer
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    List<ComputerDTO> fetchPageDTO(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column, Connection connection) throws SQLException, DAOException;

    /**
     * Retrieve the number of computers.
     *
     * @param search     the filter for the search
     * @param connection the connection from the pool
     * @return the number of computers
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    int count(String search, Connection connection) throws SQLException, DAOException;
}
