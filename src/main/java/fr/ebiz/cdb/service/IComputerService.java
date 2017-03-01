package fr.ebiz.cdb.service;


import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;

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
    Computer get(String id) throws ComputerException, InputValidationException;

    /**
     * Retrieve the computer for the given id.
     *
     * @param id the id of the computer
     * @return the computer for the given id
     * @throws InputValidationException exception raised when id is not valid
     * @throws ComputerException        exception raised when computer is not found
     */
    ComputerDTO getDTO(String id) throws ComputerException, InputValidationException;

    /**
     * Add the computer with the given parameters.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return true if the computer is added
     * @throws InputValidationException exception raised when id is not valid
     * @throws CompanyException         exception raised when company is not found
     */
    boolean add(String name, String introduced, String discontinued, String companyId) throws CompanyException, InputValidationException;

    /**
     * Update the computer with the given parameters.
     *
     * @param id           the id of the computer
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company
     * @return true if the computer is added
     * @throws InputValidationException exception raised when id is not valid
     * @throws CompanyException         exception raised when company is not found
     * @throws ComputerException        exception raised when computer is not found
     */
    boolean update(String id, String name, String introduced, String discontinued, String companyId) throws InputValidationException, CompanyException, ComputerException;

    /**
     * Delete the given computer.
     *
     * @param idList the list of computer to delete
     * @return true if the computer is deleted
     * @throws InputValidationException exception raised when id is not valid
     * @throws ComputerException        exception raised when a computer is not found
     */
    boolean delete(String[] idList) throws InputValidationException, ComputerException;

    /**
     * Retrieve the paged computer list for the given page and the number of item per page.
     *
     * @param page   the page index
     * @param limit  the number of computer to display per page
     * @param search the filter for the search
     * @return the pager containing the computer page
     * @throws InputValidationException exception raised if parameters are not valid
     */
    ComputerPagerDTO getPagedComputerDTOList(String page, String limit, String search) throws InputValidationException;

    /**
     * Set the computerDAO.
     *
     * @param computerDAO the computerDAO
     */
    void setComputerDAO(IComputerDAO computerDAO);

}
