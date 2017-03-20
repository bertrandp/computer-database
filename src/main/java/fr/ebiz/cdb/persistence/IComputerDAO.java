package fr.ebiz.cdb.persistence;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.utils.DAOException;

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
     */
    Computer fetchById(int id) throws DAOException;

    /**
     * Retrieve the computerDTO for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     * @throws DAOException exception raised if there is an error with DAO
     */
    ComputerDTO fetchDTOById(Integer id) throws DAOException;

    /**
     * Add a computer.
     *
     * @param computer the computer to add
     * @return <tt>true</tt> if the computer is added
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean add(Computer computer) throws DAOException;

    /**
     * Update a computer.
     *
     * @param computer the computer to update
     * @return <tt>true</tt> if the computer is updated
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean update(Computer computer) throws DAOException;

    /**
     * Delete a computer.
     *
     * @param computerId the id of the computer to delete
     * @return <tt>true</tt> if the computer is deleted
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean delete(int computerId) throws DAOException;

    /**
     * Delete all the computers for the given company id.
     *
     * @param id the id of the company
     * @return <tt>true</tt> if the computers are deleted
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean deleteByCompanyId(Integer id) throws DAOException;

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
     */
    List<ComputerDTO> fetchPageDTO(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column) throws DAOException;

    /**
     * Retrieve the number of computers.
     *
     * @param search the filter for the search
     * @return the number of computers
     * @throws DAOException exception raised if there is an error with DAO
     */
    int count(String search) throws DAOException;


}
