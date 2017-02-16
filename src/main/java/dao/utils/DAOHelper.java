package main.java.dao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOHelper {

    private static Logger logger = LoggerFactory.getLogger(DAOHelper.class);
    private static final String PROPERTIES = "dao.properties";

    public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objects.length; i++ ) {
            preparedStatement.setObject( i + 1, objects[i] );
        }
        return preparedStatement;
    }

    public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(preparedStatement != null ) {
                preparedStatement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close database connection : " + e.getMessage());
        }
    }

    public static void closeConnection(PreparedStatement preparedStatement, Connection connection) {
        try {
            if(preparedStatement != null ) {
                preparedStatement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close database connection : " + e.getMessage());
        }
    }

    public static Properties readPropertiesFile() {

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream( PROPERTIES );

        if ( propertiesFile == null ) {
            throw new DAOConfigurationException( "File " + PROPERTIES + " not found." );
        }

        try {
            properties.load( propertiesFile );

        } catch (IOException e) {
            throw new DAOConfigurationException( "Impossible to load property file " + PROPERTIES, e );
        }

        return properties;
    }
}
