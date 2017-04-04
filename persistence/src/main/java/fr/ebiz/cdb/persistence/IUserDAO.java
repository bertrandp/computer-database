package fr.ebiz.cdb.persistence;

import fr.ebiz.cdb.core.User;

/**
 * Created by Bertrand Pestre on 03/04/17.
 */
public interface IUserDAO {

    /**
     * Get user.
     *
     * @param username username
     * @return the user
     */
    User getByName(String username);

}
