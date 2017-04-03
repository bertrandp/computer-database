package fr.ebiz.cdb.persistence;


import fr.ebiz.cdb.binding.ComputerPagerDTO;
import fr.ebiz.cdb.core.Computer;

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
     * Add a computer.
     *
     * @param computer the computer to add
     * @return <tt>true</tt> if the computer is added
     */
    boolean add(Computer computer);

    /**
     * Update a computer.
     *
     * @param computer the computer to update
     * @return <tt>true</tt> if the computer is updated
     */
    boolean update(Computer computer);

    /**
     * Delete a computer.
     *
     * @param computerId the id of the computer to delete
     * @return <tt>true</tt> if the computer is deleted
     */
    boolean delete(int computerId);

    /**
     * Delete all the computers for the given company id.
     *
     * @param id the id of the company
     * @return <tt>true</tt> if the computers are deleted
     */
    boolean deleteByCompanyId(Integer id);

    /**
     * Retrieve a list of computerDTO with a specific limit and offset.
     *
     * @param limit  the number of computer to retrieve
     * @param offset the index of the first computer of the list
     * @param search the filter for the search
     * @param order  the order of the sort
     * @param column the column of the sort
     * @return the list of computerDTO
     */
    List fetchPage(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column);

    /**
     * Retrieve the number of computers.
     *
     * @param search the filter for the search
     * @return the number of computers
     */
    int count(String search);

}
