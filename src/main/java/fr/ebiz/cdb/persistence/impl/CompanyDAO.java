package fr.ebiz.cdb.persistence.impl;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ICompanyDAO;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bpestre on 14/02/17.
 */
@Repository
public class CompanyDAO implements ICompanyDAO {

    private static final String HQL_SELECT = "from company";
    private static final String HQL_DELETE = "delete from company where id = :id";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Company> fetchAll() {
        return sessionFactory.getCurrentSession().createQuery(HQL_SELECT).list();
    }

    @Override
    public boolean delete(Integer id) {
        return sessionFactory.getCurrentSession().createQuery(HQL_DELETE).setParameter("id", id).executeUpdate() == 1;
    }

}
