package fr.ebiz.cdb.service;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.utils.DAOException;

import java.sql.SQLException;
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
    ComputerDTO getDTO(Integer id) throws DAOException;

    /**
     * Add the given computer.
     *
     * @param computer the computer to add
     * @return true if the computer is added
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean add(Computer computer) throws DAOException;

    /**
     * Update the given computer.
     *
     * @param computer the computer to update
     * @return true if the computer is updated
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean update(Computer computer) throws DAOException;

    /**
     * Delete the given computer.
     *
     * @param idList the list of computer to delete
     * @return true if the computers are deleted
     * @throws DAOException exception raised if there is an error with DAO
     */
    boolean delete(List<Integer> idList) throws DAOException;

    /**
     * Fill the list of computer for the given computerPagerDTO.
     *
     * @param page the page index
     * @return the computerPagerDTO with the list of computer
     * @throws DAOException exception raised if there is an error with DAO
     * @throws SQLException exception raised if there is an error with the datasource
     */
    ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) throws DAOException, SQLException;

    /**
     * Set the computerDAO.
     *
     * @param computerDAO the computerDAO
     */
    void setComputerDAO(IComputerDAO computerDAO);

}
