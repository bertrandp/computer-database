package fr.ebiz.cdb.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by bpestre on 06/03/17.
 */
public class ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    public static final String FAILED_TO_GET_CONNECTION_PROM_THE_POOL = "Failed to get connection prom the pool";
    private static ThreadLocal<Connection> connection = ThreadLocal.withInitial(() -> {
        try {
            return ConnectionPool.INSTANCE.getConnection();
        } catch (SQLException e) {
            LOGGER.error(FAILED_TO_GET_CONNECTION_PROM_THE_POOL);
            return null;
        }
    });

    public static Connection getConnection(){
        return connection.get();
    }

    public static void closeConnection() throws SQLException {
        connection.get().close();
        connection.remove();
    }
}
