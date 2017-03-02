package fr.ebiz.cdb.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.ebiz.cdb.dao.utils.DAOConfigurationException;
import fr.ebiz.cdb.dao.utils.DAOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class that handles the connection with the database.
 * <p>
 * <p>
 * Created by bpestre on 14/02/17.
 */
public enum ConnectionPool {

    INSTANCE;

    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static HikariDataSource ds;

    /**
     * ConnectionPool constructor. Fetch the database connection properties and instantiate the driver.
     */
    static {

        Properties properties = null;
        try {
            properties = DAOHelper.readPropertiesFile();
        } catch (DAOConfigurationException e) {
            LOGGER.error(e.getMessage());
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty(PROPERTY_URL));
        config.setUsername(properties.getProperty(PROPERTY_USERNAME));
        config.setPassword(properties.getProperty(PROPERTY_PASSWORD));
        config.setDriverClassName(properties.getProperty(PROPERTY_DRIVER));
        config.addDataSourceProperty(CACHE_PREP_STMTS, "true");
        config.addDataSourceProperty(PREP_STMT_CACHE_SIZE, "250");
        config.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, "2048");
        config.setAutoCommit(false);

        ds = new HikariDataSource(config);
    }

    /**
     * Retrieve driver connection.
     *
     * @return Connection
     * @throws SQLException exception raised if the database connection fails
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
