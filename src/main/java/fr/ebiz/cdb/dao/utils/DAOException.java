package fr.ebiz.cdb.dao.utils;

import java.sql.SQLException;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOException extends Exception {

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

    /**
     * Exception related to the database.
     *
     * @param throwable the exception raised
     * @param message   the error message
     */
    public DAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
