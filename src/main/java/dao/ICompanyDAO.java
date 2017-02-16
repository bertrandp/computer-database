package main.java.dao;

import main.java.model.Company;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyDAO {

    List<Company> fetchAll();
    Company fetch(int id);
    Company fetch(String name);

}
