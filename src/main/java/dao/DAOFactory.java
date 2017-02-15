package main.java.dao;

import main.java.dao.impl.CompanyDAO;

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

    private static final String PROPERTIES          = "/main/resources/dao.properties";
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

        /*Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        //File  propertiesFile = new File("/home/ebiz/cdb/training-java/config/dao/propertiesFile");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream( PROPERTIES );

        if ( propertiesFile == null ) {
            throw new DAOConfigurationException( "File " + PROPERTIES + " not found." );
        }

        try {
            properties.load( propertiesFile );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            username = properties.getProperty( PROPERTY_USERNAME );
            password = properties.getProperty( PROPERTY_PASSWORD );
        } catch (IOException e) {
            throw new DAOConfigurationException( "Impossible to load property file " + PROPERTIES, e );
        }*/

        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver not found ", e );
        }

        DAOFactory instance = new DAOFactory( "jdbc:mysql://localhost:3306/computer-database-db", "admincdb", "qwerty1234" );
        return instance;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/computer-database-db", "admincdb", "qwerty1234" );
    }

    public ICompanyDAO CompanyDAO() {
        return new CompanyDAO( this );
    }

}
