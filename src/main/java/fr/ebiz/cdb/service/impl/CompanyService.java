package fr.ebiz.cdb.service.impl;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ICompanyDAO;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
@Service
public class CompanyService implements ICompanyService {


    private static final String COMPANY_NOT_FOUND = "Company not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IComputerDAO computerDAO;

    @Override
    public List<Company> fetchAll() {
        return companyDAO.fetchAll();
    }

    @Override
    public Company fetchById(Integer companyId) throws DAOException {
        Company company = companyDAO.fetch(companyId);
        if (company == null) {
            throw new DAOException(COMPANY_NOT_FOUND);
        }
        return company;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        computerDAO.deleteByCompanyId(id);
        companyDAO.delete(id);
        return true;
    }
}
