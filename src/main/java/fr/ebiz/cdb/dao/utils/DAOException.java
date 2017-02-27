package fr.ebiz.cdb.dao.utils;

import java.sql.SQLException;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOException extends RuntimeException {

    /**
     * Exception related to SQL.
     *
     * @param e the exception raised
     */
    public DAOException(SQLException e) {
        super(e);
    }

    /**
     * Exception related to the database.
     *
     * @param s the error message
     */
    public DAOException(String s) {
        super(s);
    }
}