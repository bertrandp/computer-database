package fr.ebiz.cdb.dao;

import fr.ebiz.cdb.dao.utils.DAOConfigurationException;
import fr.ebiz.cdb.dao.utils.DAOHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class that handles the connection with the database.
 * <p>
 * <p>
 * Created by bpestre on 14/02/17.
 */
public enum DAOFactory {

    INSTANCE;

    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private String url;
    private String username;
    private String password;

    /**
     * DAOFactory constructor. Fetch the database connection properties and instantiate the driver.
     */
    DAOFactory() {

        Properties properties = DAOHelper.readPropertiesFile();
        this.url = properties.getProperty(PROPERTY_URL);
        this.username = properties.getProperty(PROPERTY_USERNAME);
        this.password = properties.getProperty(PROPERTY_PASSWORD);

        String driver = properties.getProperty(PROPERTY_DRIVER);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Driver not found ", e);
        }
    }

    /**
     * Retrieve driver connection.
     *
     * @return Connection
     * @throws SQLException exception raised if the database connection fails
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
