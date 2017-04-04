package fr.ebiz.cdb.service;


import fr.ebiz.cdb.binding.ComputerPagerDTO;
import fr.ebiz.cdb.core.Computer;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface IComputerService {

    /**
     * Retrieve the computer for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     */
    Computer get(Integer id);

    /**
     * Add the given computer.
     *
     * @param computer the computer to add
     * @return true if the computer is added
     */
    boolean add(Computer computer);

    /**
     * Update the given computer.
     *
     * @param computer the computer to update
     * @return true if the computer is updated
     */
    boolean update(Computer computer);

    /**
     * Delete the given computer.
     *
     * @param idList the list of computer to delete
     * @return true if the computers are deleted
     */
    boolean delete(List<Integer> idList);

    /**
     * Fill the list of computer for the given computerPagerDTO.
     *
     * @param page the page index
     * @return the computerPagerDTO with the list of computer
     */
    ComputerPagerDTO fetchComputerList(ComputerPagerDTO page);

}
