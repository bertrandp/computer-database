package fr.ebiz.cdb.service.impl;


import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.impl.CompanyDAO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.InputValidationException;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public enum CompanyService implements ICompanyService {

    INSTANCE;

    private ICompanyDAO companyDAO;

    /**
     * Company constructor. Fetch the instance of DAOFactory.
     */
    CompanyService() {
        this.companyDAO = CompanyDAO.INSTANCE;
    }

    @Override
    public List<Company> fetchAll() {
        return companyDAO.fetchAll();
    }

    @Override
    public Company fetchById(Integer companyId) throws InputValidationException, CompanyException {
        Company company = companyDAO.fetch(companyId);
        if (company == null) {
            throw new CompanyException("Company with id = " + companyId + " not found");
        }
        return company;
    }
}
