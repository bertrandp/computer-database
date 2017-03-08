package fr.ebiz.cdb.persistence.mapper;

import fr.ebiz.cdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpestre on 23/02/17.
 */
public class CompanyMapper {

    private static final String ID = "id";
    private static final String NAME = "name";

    /**
     * Map the result set to a company.
     *
     * @param resultSet the result set to map
     * @return the company
     * @throws SQLException exception raised if the there is an issue with the database
     */
    public static Company mapToCompany(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Company(resultSet.getInt(ID), resultSet.getString(NAME));
        }
        return null;
    }

    /**
     * Map the result set to a list of company.
     *
     * @param resultSet the result set to map
     * @return the list of company
     * @throws SQLException exception raised if the there is an issue with the database
     */
    public static List<Company> mapToCompanyList(ResultSet resultSet) throws SQLException {
        List<Company> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Company(resultSet.getInt(ID), resultSet.getString(NAME)));
        }
        return list;
    }
}
