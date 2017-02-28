package fr.ebiz.cdb.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.ebiz.cdb.dao.utils.DAOHelper;

import java.sql.Connection;
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

    private static HikariDataSource ds;

    /**
     * DAOFactory constructor. Fetch the database connection properties and instantiate the driver.
     */
    static {

        Properties properties = DAOHelper.readPropertiesFile();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty(PROPERTY_URL));
        config.setUsername(properties.getProperty(PROPERTY_USERNAME));
        config.setPassword(properties.getProperty(PROPERTY_PASSWORD));
        config.setDriverClassName(properties.getProperty(PROPERTY_DRIVER));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
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
        //return DriverManager.getConnection(url, username, password);
        return ds.getConnection();
    }

}
