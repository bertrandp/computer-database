package fr.ebiz.cdb.service;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;

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
     * @throws DAOException exception raised when there is an error with DAO
     */
    ComputerDTO getDTO(Integer id);

    /**
     * Add the given computer.
     *
     * @param computer the computer to add
     * @return true if the computer is added
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean add(Computer computer);

    /**
     * Update the given computer.
     *
     * @param computer the computer to update
     * @return true if the computer is updated
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean update(Computer computer);

    /**
     * Delete the given computer.
     *
     * @param idList the list of computer to delete
     * @return true if the computers are deleted
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean delete(List<Integer> idList);

    /**
     * Fill the list of computer for the given computerPagerDTO.
     *
     * @param page the page index
     * @return the computerPagerDTO with the list of computer
     * @throws DAOException exception raised if there is an error with DAO
     */
    ComputerPagerDTO fetchComputerList(ComputerPagerDTO page);

}
