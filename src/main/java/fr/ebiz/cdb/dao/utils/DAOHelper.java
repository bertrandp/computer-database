package fr.ebiz.cdb.dao.utils;

import fr.ebiz.cdb.dto.ComputerPagerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOHelper {

    public static final String ERROR_LOADING_PROPERTIES_FILE = "Error loading properties file";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String C1_NAME = "c1.name";
    public static final String C1_INTRODUCED = "c1.introduced";
    public static final String C1_DISCONTINUED = "c1.discontinued";
    public static final String C2_NAME = "c2.name";
    public static final String ASC = " ASC";
    public static final String DESC = " DESC";
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
    public static Properties readPropertiesFile() throws DAOConfigurationException {

        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES)) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException(ERROR_LOADING_PROPERTIES_FILE, e);
        }

        return properties;
    }

    public static String buildOrderByQuery(ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column) {
        String query = ORDER_BY;
        switch (column) {
            case NAME:
                query += C1_NAME;
                break;
            case INTRODUCED:
                query += C1_INTRODUCED;
                break;
            case DISCONTINUED:
                query += C1_DISCONTINUED;
                break;
            case COMPANY_NAME:
                query += C2_NAME;
                break;
        }
        switch (order) {
            case ASC:
                query += ASC;
                break;
            case DESC:
                query += DESC;
                break;
        }
        return query;
    }


    /**
     * Convert LocalDate do database date.
     *
     * @param date the LocalDate to convert
     * @return the converted date
     */
    public static Object convertToDatabaseColumn(LocalDate date) {
        if (date != null) {
            return Date.valueOf(date);
        } else {
            return null;
        }
    }
}
