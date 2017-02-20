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

    @Override
    public List<Company> fetchAll() {
        List<Company> listCompany = null;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ICompanyDAO companyDAO = daoFactory.getCompanyDAO();
            listCompany = companyDAO.fetchAll();
        } catch (DAOConfigurationException e) {
            e.printStackTrace(); // TODO remove printstacktrace
        }
        return listCompany;
    }

    @Override
    public boolean alreadyExists(String name) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ICompanyDAO companyDAO = daoFactory.getCompanyDAO();
        Company company = companyDAO.fetch(name);
        return company != null;
    }
}
