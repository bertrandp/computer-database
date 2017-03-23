package fr.ebiz.cdb.persistence.utils;

import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ebiz on 15/02/17.
 */
public class DAOHelper {

    private static final String ORDER_BY = " order by ";
    private static final String C1_NAME = "cptr.name";
    private static final String C1_INTRODUCED = "cptr.introduced";
    private static final String C1_DISCONTINUED = "cptr.discontinued";
    private static final String C2_NAME = "cptr.company.name";
    private static final String ASC = " asc";
    private static final String DESC = " desc";
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

}
