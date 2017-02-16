package main.java.dao.utils;

/**
 * Created by ebiz on 14/02/17.
 */
public class DAOConfigurationException extends RuntimeException {

    public DAOConfigurationException(String s, Throwable e) {
        super(s,e);
    }

    public DAOConfigurationException(String s) {
        super(s);
    }
}
