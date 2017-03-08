package fr.ebiz.cdb.dao;


import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;

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
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    Computer fetchById(int id) throws SQLException, DAOException;

    /**
     * Retrieve the computerDTO for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    ComputerDTO fetchDTOById(Integer id) throws SQLException, DAOException;

    /**
     * Add a computer.
     *
     * @param computer the computer to add
     * @return <tt>true</tt> if the computer is added
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean add(Computer computer) throws SQLException, DAOException;

    /**
     * Update a computer.
     *
     * @param computer the computer to update
     * @return <tt>true</tt> if the computer is updated
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean update(Computer computer) throws SQLException, DAOException;

    /**
     * Delete a computer.
     *
     * @param computerId the id of the computer to delete
     * @return <tt>true</tt> if the computer is deleted
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean delete(int computerId) throws SQLException, DAOException;

    /**
     * Delete all the computers for the given company id.
     *
     * @param id the id of the company
     * @return <tt>true</tt> if the computers are deleted
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    boolean deleteByCompanyId(Integer id) throws SQLException, DAOException;

    /**
     * Retrieve a list of computerDTO with a specific limit and offset.
     *
     * @param limit  the number of computer to retrieve
     * @param offset the index of the first computer of the list
     * @param search the filter for the search
     * @param order  the order of the sort
     * @param column the column of the sort
     * @return the list of computerDTO
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    List<ComputerDTO> fetchPageDTO(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column) throws SQLException, DAOException;

    /**
     * Retrieve the number of computers.
     *
     * @param search the filter for the search
     * @return the number of computers
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is a issue during the transaction
     */
    int count(String search) throws SQLException, DAOException;


}
