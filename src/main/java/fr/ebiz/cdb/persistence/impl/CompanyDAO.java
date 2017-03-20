package fr.ebiz.cdb.persistence.impl;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ICompanyDAO;
import fr.ebiz.cdb.persistence.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by bpestre on 14/02/17.
 */
@Repository
public class CompanyDAO implements ICompanyDAO {

    private static final String SQL_SELECT = "SELECT * FROM company";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM company WHERE id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Company> fetchAll() {
        return jdbcTemplate.query(SQL_SELECT, new CompanyMapper());
    }

    @Override
    public Company fetch(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, Company.class, id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(SQL_DELETE, id) != 0;
    }

}
