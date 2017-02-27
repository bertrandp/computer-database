package fr.ebiz.cdb.service.impl;


import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.impl.CompanyDAO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.validation.InputValidator;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class CompanyService implements ICompanyService {

    private ICompanyDAO companyDAO;

    /**
     * Company fr.ebiz.cdb.service constructor. Fetch the instance of DAOFactory.
     */
    public CompanyService() {
        this.companyDAO = CompanyDAO.INSTANCE;
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

    @Override
    public Company fetchById(String companyId) throws InputValidationException, CompanyException {
        Integer id = InputValidator.validateInputInteger(companyId);
        Company company = companyDAO.fetch(id);
        if (company == null) {
            throw new CompanyException("Company with id = " + id + " not found");
        }
        return company;
    }
}
