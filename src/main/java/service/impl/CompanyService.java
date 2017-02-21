package service.impl;


import dao.DAOFactory;
import dao.ICompanyDAO;
import dao.utils.DAOConfigurationException;
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
        List<Company> listCompany = null;
        try {
            listCompany = companyDAO.fetchAll();
        } catch (DAOConfigurationException e) {
            e.printStackTrace(); // TODO remove printstacktrace
        }
        return listCompany;
    }

    @Override
    public boolean nameAlreadyExists(String name) {
        Company company = companyDAO.fetch(name);
        return company != null;
    }

    @Override
    public boolean idAlreadyExists(int id) {
        Company company = companyDAO.fetch(id);
        return company != null;
    }
}
