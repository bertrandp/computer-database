package fr.ebiz.cdb.dao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOHelper {

    private static final String PROPERTIES = "dao.properties";
    private static Logger logger = LoggerFactory.getLogger(DAOHelper.class);

    /**
     * Initialize the prepared statement with the given query and list of parameters.
     *
     * @param connection          the connection of the the prepared statement
     * @param sql                 the sql query
     * @param returnGeneratedKeys whether the prepared statement should return auto generated values or not
     * @param objects             list of parameters to set the query
     * @return the prepared statement
     * @throws SQLException exception raised when there is an sql error
     */
    public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    /**
     * Read the property file.
     *
     * @return the properties
     */
    public static Properties readPropertiesFile() {

        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES)) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible to load property file " + PROPERTIES, e);
        }

        return properties;
    }
}
