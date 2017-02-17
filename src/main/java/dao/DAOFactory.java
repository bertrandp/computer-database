package dao;

import dao.impl.CompanyDAO;
import dao.impl.ComputerDAO;
import dao.utils.DAOConfigurationException;
import dao.utils.DAOHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class that handles the connection with the database.
 *
 *
 * Created by bpestre on 14/02/17.
 */
public class DAOFactory {

    private static final String PROPERTY_URL        = "url";
    private static final String PROPERTY_DRIVER     = "driver";
    private static final String PROPERTY_USERNAME   = "username";
    private static final String PROPERTY_PASSWORD   = "password";

    private String url;
    private String username;
    private String password;

    private static DAOFactory factoryInstance = null;

    private DAOFactory() {

        Properties properties = DAOHelper.readPropertiesFile();
        this.url = properties.getProperty( PROPERTY_URL );
        this.username = properties.getProperty( PROPERTY_USERNAME );
        this.password = properties.getProperty( PROPERTY_PASSWORD );

        String driver = properties.getProperty( PROPERTY_DRIVER );
        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver not found ", e );
        }
    }

    /**
     * Retrieve DAOFactory instance
     * @return instance of DAOFactory
     * @throws DAOConfigurationException
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {

        if(factoryInstance == null) {
            synchronized(DAOFactory.class) {
                if(factoryInstance == null) {
                    factoryInstance = new DAOFactory();
                }
            }
        }
        return factoryInstance;
    }

    /**
     * Retrieve driver connection
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    /**
     * Retrieve implementation of ICompanyDAO
     * @return ICompanyDAO
     */
    public ICompanyDAO CompanyDAO() {
        return new CompanyDAO( this );
    }

    /**
     * Retrieve implementation of IComputerDAO
     * @return IComputerDAO
     */
    public IComputerDAO ComputerDAO() {
        return new ComputerDAO( this );
    }


}
