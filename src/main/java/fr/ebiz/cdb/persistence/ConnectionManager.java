package fr.ebiz.cdb.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bpestre on 06/03/17.
 */
@Component
public class ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    @Autowired
    private DataSource dataSource;

    private ThreadLocal<Connection> connection = ThreadLocal.withInitial(() -> {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Failed to get connection from datasource");
            return null;
        }
    });

    public Connection getConnection() {
        return connection.get();
    }

    /**
     * Return a connection with autoCommit set to false.
     *
     * @return the transactional connection
     */
    public Connection getTransactionalConnection() {
        Connection conn = connection.get();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Failed to set autoCommit to false");
            return null;
        }
        return conn;
    }

    /**
     * Remove de connection from the ThreadLocal and close it.
     *
     * @throws SQLException exception raised if there is an error when closing the connection
     */
    public void closeConnection() throws SQLException {
        connection.get().close();
        connection.remove();
    }

}
