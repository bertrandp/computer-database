package fr.ebiz.cdb.persistence.utils;

/**
 * Created by ebiz on 14/02/17.
 */
public class DAOConfigurationException extends Exception {

    /**
     * Exception related to database connection.
     *
     * @param s the error message
     * @param e the exception raised
     */
    public DAOConfigurationException(String s, Throwable e) {
        super(s, e);
    }

}
