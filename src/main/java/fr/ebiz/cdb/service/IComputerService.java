package fr.ebiz.cdb.service;


import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.validation.InputValidationException;

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
     * @throws DAOException exception raised when computer is not found
     */
    ComputerDTO getDTO(Integer id) throws DAOException;

    /**
     * Add the given computer.
     *
     * @param computer the computer to add
     * @return true if the computer is added
     */
    boolean add(Computer computer) throws DAOException;

    /**
     * Update the given computer.
     *
     * @param computer the computer to update
     * @return true if the computer is updated
     */
    boolean update(Computer computer) throws DAOException;

    /**
     * Delete the given computer.
     *
     * @param idList the list of computer to delete
     * @return true if the computer is deleted
     * @throws DAOException exception raised when a computer is not found
     */
    boolean delete(List<Integer> idList) throws DAOException;

    /**
     * Set the computerDAO.
     *
     * @param computerDAO the computerDAO
     */
    void setComputerDAO(IComputerDAO computerDAO);

    /**
     * Fill the list of computer for the given computerPagerDTO.
     *
     * @param page the page index
     * @return the computerPagerDTO with the list of computer
     * @throws InputValidationException exception raised if parameters are not valid
     */
    ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) throws DAOException;


}
