package dao.utils;

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

    private static final String PROPERTIES = "dao.properties";
    private static Logger logger = LoggerFactory.getLogger(DAOHelper.class);

    /**
     * Initialize the prepared statement with the given query and list of parameters
     *
     * @param connection          the connection of the the prepared statement
     * @param sql                 the sql query
     * @param returnGeneratedKeys whether the prepared statement should return auto generated values or not
     * @param objects             list of parameters to set the query
     * @return the prepared statement
     * @throws SQLException
     */
    public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    /**
     * Close the given resultSet, prepared statement and connection
     *
     * @param resultSet         the result set to close
     * @param preparedStatement the prepared statement to close
     * @param connection        the connection to close
     */
    public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close the resultSet : " + e.getMessage());
        }
        closeConnection(preparedStatement, connection);
    }

    /**
     * Close the given prepared statement and connection
     *
     * @param preparedStatement the prepared statement to close
     * @param connection        the connection to close
     */
    public static void closeConnection(PreparedStatement preparedStatement, Connection connection) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close the prepared statement : " + e.getMessage());
        }
        closeConnection(connection);
    }

    /**
     * Close the given connection
     *
     * @param connection the connection to close
     */
    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close database connection : " + e.getMessage());
        }
    }

    /**
     * Read the property file
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
