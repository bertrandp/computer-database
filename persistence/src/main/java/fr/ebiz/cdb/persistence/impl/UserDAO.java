package fr.ebiz.cdb.persistence.impl;

import fr.ebiz.cdb.core.User;
import fr.ebiz.cdb.persistence.IUserDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Bertrand Pestre on 03/04/17.
 */
@Repository
public class UserDAO implements IUserDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getByName(String username) {
        return (User) sessionFactory.getCurrentSession().createQuery("from user where username = :username")
                .setParameter("username", username)
                .list().get(0);
    }

}
