package service;


import model.Company;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyService {

    /**
     * Retrieve the list of company.
     *
     * @return the list of company
     */
    List<Company> fetchAll();

    /**
     * Check if company already exists for the given name.
     *
     * @param name the name of the company
     * @return true if the company already exists
     */
    boolean nameAlreadyExists(String name);

    /**
     * Check if company already exists for the given id.
     *
     * @param id the name of the company
     * @return true if the company already exists
     */
    boolean idAlreadyExists(int id);
}
