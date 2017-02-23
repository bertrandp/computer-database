package service.impl;


import dao.DAOFactory;
import dao.ICompanyDAO;
import model.Company;
import service.ICompanyService;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class CompanyService implements ICompanyService {

    private DAOFactory daoFactory;
    private ICompanyDAO companyDAO;

    /**
     * Company service constructor. Fetch the instance of DAOFactory.
     */
    public CompanyService() {
        this.daoFactory = DAOFactory.getInstance();
        this.companyDAO = daoFactory.getCompanyDAO();
    }

    @Override
    public List<Company> fetchAll() {
        return companyDAO.fetchAll();
    }

    @Override
    public Company fetch(String name) {
        return companyDAO.fetch(name);
    }

    @Override
    public Company fetch(int id) {
        return companyDAO.fetch(id);
    }
}
