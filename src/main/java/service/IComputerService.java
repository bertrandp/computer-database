package service;


import dto.ComputerDTO;
import dto.ComputerPagerDTO;
import model.Computer;
import model.Pager;
import service.utils.ComputerValidationException;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface IComputerService {

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
     * @throws ComputerValidationException exception raised when id is not valid
     */
    Computer get(String id) throws ComputerValidationException;

    /**
     * Retrieve the computer for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     * @throws ComputerValidationException exception raised when id is not valid
     */
    ComputerDTO getDTO(String id) throws ComputerValidationException;

    /**
     * Add the computer with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyName  the name of the company
     * @return true if the computer is added
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    boolean addWithCompanyName(String name, String introduced, String discontinued, String companyName) throws ComputerValidationException;

    /**
     * Add the computer with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return true if the computer is added
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    boolean addWithCompanyId(String name, String introduced, String discontinued, String companyId) throws ComputerValidationException;

    /**
     * Update the computer with the given parameters.
     *
     * @param id           the id of the computer
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return true if the computer is added
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    boolean updateWithCompanyId(String id, String name, String introduced, String discontinued, String companyId) throws ComputerValidationException;

    /**
     * Update the computer with the given parameters.
     *
     * @param id           the id of the computer
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyName  the name of the company
     * @return true if the computer is added
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    boolean updateWithCompanyName(String id, String name, String introduced, String discontinued, String companyName) throws ComputerValidationException;

    /**
     * Delete the given computer.
     *
     * @param computerId the computer to delete
     * @return true if the computer is deleted
     */
    boolean delete(int computerId);

    /**
     * Retrieve the total number of computers.
     *
     * @return the total number of computers
     */
    int count();

    /**
     * Retrieve the paged computer list for the given page and the number of item per page.
     *
     * @param page  the page index
     * @param limit the number of computer to display per page
     * @return the pager containing the computer page
     * @throws ComputerValidationException exception raised if parameters are not valid
     */
    ComputerPagerDTO getPagedComputerDTOList(String page, String limit) throws ComputerValidationException;



}
