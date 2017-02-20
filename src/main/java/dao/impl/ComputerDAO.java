package dao.impl;

import dao.DAOFactory;
import dao.ICompanyDAO;
import dao.IComputerDAO;
import dao.utils.DAOException;
import dao.utils.DAOHelper;
import model.Company;
import model.Computer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public class ComputerDAO implements IComputerDAO {

    private static final String SQL_SELECT = "SELECT * FROM computer";
    private static final String SQL_COUNT = "SELECT count(*) AS total FROM computer";
    private static final String SQL_SELECT_PAGE = "SELECT * FROM computer LIMIT ? OFFSET ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id ) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";
    private DAOFactory daoFactory;

    /**
     * ComputerDAO constructor.
     *
     * @param daoFactory the DAOFactory
     */
    public ComputerDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Computer> fetchAll() {
        List<Computer> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = mapResultSetToComputerList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }

    @Override
    public Computer fetchById(int id) {
        Computer computer;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT_BY_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            computer = mapResultSetToComputer(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return computer;
    }

    @Override
    public boolean add(Computer computer) {
        Integer companyId = validateCompany(computer);

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_INSERT, true, computer.getName(), convertToDatabaseColumn(computer.getIntroduced()), convertToDatabaseColumn(computer.getDiscontinued()), companyId);
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException("Failed to create computer : " + computer);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return true;
    }

    /**
     * Convert LocalDate do database date.
     *
     * @param date the LocalDate to convert
     * @return the converted date
     */
    private Object convertToDatabaseColumn(LocalDate date) {
        if (date != null) {
            return Date.valueOf(date);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(Computer computer) {
        Integer companyId = validateCompany(computer);

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_UPDATE, true, computer.getName(), convertToDatabaseColumn(computer.getIntroduced()), convertToDatabaseColumn(computer.getDiscontinued()), companyId, computer.getId());
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException("Failed to update computer : " + computer);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return true;
    }

    /**
     * Validate the given company of the computer and return the id of the company if it is valid.
     *
     * @param computer the computer to valid
     * @return the id of the company if the id is valid else return null
     */
    private Integer validateCompany(Computer computer) {
        Integer companyId = null;
        if (computer.getCompany() != null) {
            ICompanyDAO companyDAO = daoFactory.getCompanyDAO();
            Company company = companyDAO.fetch(computer.getCompany().getName());
            if (company == null) {
                throw new DAOException("Failed to create computer : " + computer.getName() + ". Company name must be an existing company");
            } else {
                companyId = company.getId();
            }
        }
        return companyId;
    }

    @Override
    public boolean delete(Computer computer) {

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_DELETE, true, computer.getId());
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException("Failed to delete computer : " + computer);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return true;
    }

    @Override
    public List<Computer> fetch(int limit, int offset) {
        List<Computer> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT_PAGE, true, limit, offset);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = mapResultSetToComputerList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }

    @Override
    public int count() {
        int count = 0;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_COUNT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return count;
    }

    /**
     * Map the result set to list of computer.
     *
     * @param resultSet the result set to map
     * @return the list of computer
     * @throws SQLException exception raised if the there is an issue with the database
     */
    private List<Computer> mapResultSetToComputerList(ResultSet resultSet) throws SQLException {
        List<Computer> list = new ArrayList<>();
        while (resultSet.next()) {
            Computer computer = new Computer();
            computer.setId(resultSet.getInt("id"));
            computer.setName(resultSet.getString("name"));
            list.add(computer);
        }
        return list;
    }

    /**
     * Map the result set to a computer.
     *
     * @param resultSet the result set to map
     * @return the computer
     * @throws SQLException exception raised if the there is an issue with the database
     */
    private Computer mapResultSetToComputer(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        if (resultSet.next()) {
            computer.setId(resultSet.getInt("id"));
            computer.setName(resultSet.getString("name"));
            Date inputIntroduced = resultSet.getDate("introduced");
            if (inputIntroduced != null) {
                computer.setIntroduced(inputIntroduced.toLocalDate());
            } else {
                computer.setIntroduced(null);
            }
            Date inputDiscontinued = resultSet.getDate("discontinued");
            if (inputDiscontinued != null) {
                computer.setDiscontinued(inputDiscontinued.toLocalDate());
            } else {
                computer.setDiscontinued(null);
            }
            ICompanyDAO companyDAO = daoFactory.getCompanyDAO();
            Company company = companyDAO.fetch(resultSet.getInt("company_id"));
            computer.setCompany(company);
            return computer;
        }
        return null;
    }
}
