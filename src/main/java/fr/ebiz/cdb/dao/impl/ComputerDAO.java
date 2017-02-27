package fr.ebiz.cdb.dao.impl;

import fr.ebiz.cdb.dao.DAOFactory;
import fr.ebiz.cdb.dao.ICompanyDAO;
import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.mapper.ComputerMapper;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dao.utils.DAOHelper;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public enum ComputerDAO implements IComputerDAO {

    INSTANCE;

    private static final String SQL_SELECT = "SELECT c1.id, c1.name, c1.introduced, c1.discontinued, c1.company_id, c2.name as company_name FROM computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id";
    private static final String SQL_COUNT = "SELECT count(*) AS total FROM computer";
    private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";
    private static final String WHERE_ID = " WHERE c1.id = ?";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id ) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";
    private DAOFactory daoFactory;

    /**
     * ComputerDAO constructor.
     */
    ComputerDAO() {
        this.daoFactory = DAOFactory.INSTANCE;
    }

    @Override
    public List<ComputerDTO> fetchAllDTO() {
        List<ComputerDTO> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = ComputerMapper.mapToComputerDTOList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return list;
    }


    @Override
    public Computer fetchById(int id) {
        Computer computer;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT + WHERE_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            computer = ComputerMapper.mapToComputer(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return computer;
    }

    @Override
    public ComputerDTO fetchDTOById(Integer id) {
        ComputerDTO computer;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT + WHERE_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            computer = ComputerMapper.mapToComputerDTO(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return computer;
    }

    @Override
    public boolean add(Computer computer) {
        Integer companyId = validateCompany(computer);

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_INSERT, true, computer.getName(), convertToDatabaseColumn(computer.getIntroduced()), convertToDatabaseColumn(computer.getDiscontinued()), companyId)
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
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_UPDATE, true, computer.getName(), convertToDatabaseColumn(computer.getIntroduced()), convertToDatabaseColumn(computer.getDiscontinued()), companyId, computer.getId())
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
            ICompanyDAO companyDAO = CompanyDAO.INSTANCE;
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
    public boolean delete(int computerId) {

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_DELETE, true, computerId)
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException("Failed to delete computer : " + computerId);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return true;
    }

    @Override
    public List<ComputerDTO> fetchPageDTO(int limit, int offset) {
        List<ComputerDTO> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT + LIMIT_OFFSET, true, limit, offset);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = ComputerMapper.mapToComputerDTOList(resultSet);
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

}
