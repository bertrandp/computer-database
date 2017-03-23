package fr.ebiz.cdb.persistence.impl;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.utils.DAOHelper;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bertrand Pestre on 15/02/17.
 */
@Repository
public class ComputerDAO implements IComputerDAO {

    public static final String HQL_FROM = " from computer as cptr ";
    public static final String HQL_JOIN = " left outer join cptr.company as cpn ";
    public static final String HQL_LIKE = " where cptr.name like :search or cpn.name like :search ";
    public static final String HQL_DELETE = " delete from computer where id = :id ";
    public static final String HQL_DELETE_BY_COMPANY_ID = " delete from computer as cptr where cptr.company.id = :id ";
    private static final String HQL_COUNT = "SELECT count(cptr)";
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Computer fetchById(int id) {
        return sessionFactory.getCurrentSession().get(Computer.class, id);
    }

    @Override
    public boolean add(Computer computer) {
        return sessionFactory.getCurrentSession().save(computer) != null;
    }

    @Override
    public boolean update(Computer computer) {
        sessionFactory.getCurrentSession().update(computer);
        return true;
    }

    @Override
    public boolean delete(int computerId) {
        return sessionFactory.getCurrentSession().createQuery(HQL_DELETE)
                .setParameter("id", computerId)
                .executeUpdate() == 1;
    }

    @Override
    public boolean deleteByCompanyId(Integer id) {
        return sessionFactory.getCurrentSession().createQuery(HQL_DELETE_BY_COMPANY_ID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Computer> fetchPage(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column) {
        String like = search == null ? "%" : "%" + search + "%";
        String orderByQuery = DAOHelper.buildOrderByQuery(order, column);

        return sessionFactory.getCurrentSession().createQuery(HQL_FROM + HQL_JOIN + HQL_LIKE + orderByQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .setParameter("search", like)
                .list();
    }

    @Override
    public int count(String search) {
        if (search == null) {
            Long nb = (Long) sessionFactory.getCurrentSession().createQuery(HQL_COUNT + HQL_FROM).list().get(0);
            return nb.intValue();
        } else {
            String like = "%" + search + "%";
            Long nb = (Long) sessionFactory.getCurrentSession().createQuery(HQL_COUNT + HQL_FROM + HQL_JOIN + HQL_LIKE)
                    .setParameter("search", like).list().get(0);
            return nb.intValue();
        }
    }

}
