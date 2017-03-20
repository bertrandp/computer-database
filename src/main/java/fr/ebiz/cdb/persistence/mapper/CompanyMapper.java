package fr.ebiz.cdb.persistence.mapper;

import fr.ebiz.cdb.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bpestre on 23/02/17.
 */
public class CompanyMapper implements RowMapper<Company> {

    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Company(resultSet.getInt(ID), resultSet.getString(NAME));
    }
}
