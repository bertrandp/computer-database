package fr.ebiz.cdb.persistence.impl;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.persistence.utils.DAOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Bertrand Pestre on 15/02/17.
 */
@Repository
public class ComputerDAO implements IComputerDAO {

    private static final String SQL_SELECT = "SELECT c1.id, c1.name, c1.introduced, c1.discontinued, c1.company_id, c2.name as company_name FROM computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id";
    private static final String COUNT_WITH_SEARCH = "SELECT count(*) AS total FROM computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id";
    private static final String SQL_COUNT = "SELECT count(*) AS total FROM computer";
    private static final String LIKE = " WHERE c1.name LIKE ? OR c2.name LIKE ? ";
    private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";
    private static final String WHERE_ID = " WHERE c1.id = ?";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id ) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";
    private static final String SQL_DELETE_BY_COMPANY_ID = "DELETE FROM computer WHERE company_id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Computer fetchById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT + WHERE_ID, new Object[]{id}, new ComputerMapper());
    }

    @Override
    public boolean add(Computer computer) {
        return jdbcTemplate.update(SQL_INSERT, computer.getName(), DAOHelper.convertToDatabaseColumn(computer.getIntroduced()), DAOHelper.convertToDatabaseColumn(computer.getDiscontinued()), computer.getCompany() == null ? null : computer.getCompany().getId()) != 0;
    }

    @Override
    public boolean update(Computer computer) {
        return jdbcTemplate.update(SQL_UPDATE, computer.getName(), DAOHelper.convertToDatabaseColumn(computer.getIntroduced()), DAOHelper.convertToDatabaseColumn(computer.getDiscontinued()), computer.getCompany() == null ? null : computer.getCompany().getId(), computer.getId()) != 0;
    }

    @Override
    public boolean delete(int computerId) {
        return jdbcTemplate.update(SQL_DELETE, computerId) != 0;
    }

    @Override
    public boolean deleteByCompanyId(Integer id) {
        return jdbcTemplate.update(SQL_DELETE_BY_COMPANY_ID, id) != 0;
    }

    @Override
    public List<Computer> fetchPage(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column) {
        String like = search == null ? "%" : "%" + search + "%";
        String orderByQuery = DAOHelper.buildOrderByQuery(order, column);
        return jdbcTemplate.query(SQL_SELECT + LIKE + orderByQuery + LIMIT_OFFSET, new Object[]{like, like, limit, offset}, new ComputerMapper());
    }

    @Override
    public int count(String search) {
        if (search == null) {
            return jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
        } else {
            String like = "%" + search + "%";
            return jdbcTemplate.queryForObject(COUNT_WITH_SEARCH + LIKE, new Object[]{like, like}, Integer.class);
        }
    }

}
