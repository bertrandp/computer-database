package main.java.dao;

import main.java.dao.impl.CompanyDAO;
import main.java.dao.impl.ComputerDAO;
import main.java.dao.utils.DAOHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ebiz on 14/02/17.
 */
public class DAOFactory {

    private static final String PROPERTY_URL        = "url";
    private static final String PROPERTY_DRIVER     = "driver";
    private static final String PROPERTY_USERNAME   = "username";
    private static final String PROPERTY_PASSWORD   = "password";

    private String url;
    private String username;
    private String password;

    public DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {

        Properties properties;
        String url;
        String driver;
        String username;
        String password;

        properties = DAOHelper.readPropertiesFile();
        url = properties.getProperty( PROPERTY_URL );
        driver = properties.getProperty( PROPERTY_DRIVER );
        username = properties.getProperty( PROPERTY_USERNAME );
        password = properties.getProperty( PROPERTY_PASSWORD );

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver not found ", e );
        }

        return new DAOFactory( url, username, password );
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    public ICompanyDAO CompanyDAO() {
        return new CompanyDAO( this );
    }

    public IComputerDAO ComputerDAO() {
        return new ComputerDAO( this );
    }


}
