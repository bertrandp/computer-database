package fr.ebiz.cdb.persistence.utils;

import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOHelper {

    private static final String ORDER_BY = " ORDER BY ";
    private static final String C1_NAME = "c1.name";
    private static final String C1_INTRODUCED = "c1.introduced";
    private static final String C1_DISCONTINUED = "c1.discontinued";
    private static final String C2_NAME = "c2.name";
    private static final String ASC = " ASC";
    private static final String DESC = " DESC";
    private static Logger logger = LoggerFactory.getLogger(DAOHelper.class);

    /**
     * Build sql query for ORDER BY.
     *
     * @param order  the sorting order
     * @param column the selected
     * @return the query
     */
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
