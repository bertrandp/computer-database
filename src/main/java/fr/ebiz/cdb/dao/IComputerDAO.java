package fr.ebiz.cdb.dao;


import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Computer;

import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public interface IComputerDAO {

    /**
     * Retrieve the list of computerDTO.
     *
     * @return the list of computerDTO
     */
    List<ComputerDTO> fetchAllDTO();

    /**
     * Retrieve the computer for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     */
    Computer fetchById(int id);

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
     * @param computerId the computer to delete
     * @return <tt>true</tt> if the computer is deleted
     */
    boolean delete(int computerId);

    /**
     * Retrieve a list of computerDTO with a specific limit and offset.
     *
     * @param limit  the number of computer to retrieve
     * @param offset the index of the first computer of the list
     * @return the list of computerDTO with the given limit and offset
     */
    List<ComputerDTO> fetchPageDTO(int limit, int offset);

    /**
     * Retrieve the number of computers.
     *
     * @return the number of computers
     */
    int count();

}
