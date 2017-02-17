package dao.utils;

import java.sql.SQLException;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOException extends RuntimeException{


    public DAOException(SQLException e) {
        super( e );
    }

    public DAOException(String s) {
        super( s );
    }
}
