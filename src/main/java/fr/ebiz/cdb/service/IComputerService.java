package fr.ebiz.cdb.service;


import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;

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
     * @throws InputValidationException exception raised when id is not valid
     * @throws ComputerException        exception raised when computer is not found
     */
    ComputerDTO getDTO(Integer id) throws ComputerException, InputValidationException;

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
     * @return true if the computer is deleted
     * @throws InputValidationException exception raised when id is not valid
     * @throws ComputerException        exception raised when a computer is not found
     */
    boolean delete(List<Integer> idList) throws InputValidationException, ComputerException;

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
    ComputerPagerDTO fetchComputerList(ComputerPagerDTO page);


}
