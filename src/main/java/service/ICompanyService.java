package main.java.service;

import main.java.model.Company;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public interface ICompanyService {

    List<Company>  fetchAll();
    boolean alreadyExists(String input);
}
