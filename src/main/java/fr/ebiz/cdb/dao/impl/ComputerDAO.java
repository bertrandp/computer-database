package fr.ebiz.cdb.dao.impl;

import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.mapper.ComputerMapper;
import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dao.utils.DAOHelper;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static fr.ebiz.cdb.dao.impl.CompanyDAO.DATABASE_CONNECTION_ERROR;
import static fr.ebiz.cdb.dao.impl.CompanyDAO.TRANSACTION_ROLLED_BACK;

/**
 * Created by ebiz on 15/02/17.
 */
public enum ComputerDAO implements IComputerDAO {

    INSTANCE;

    public static final String ERROR_CREATING_THE_COMPUTER = "Error creating the computer";
    public static final String ERROR_UPDATING_THE_COMPUTER = "Error updating the computer";
    public static final String ERROR_DELETING_THE_COMPUTER = "Error deleting the computer";
    public static final String TOTAL = "total";
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

    @Override
    public Computer fetchById(int id, Connection connection) throws SQLException, DAOException {
        Computer computer;

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT + WHERE_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            computer = ComputerMapper.mapToComputer(resultSet);
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return computer;
    }

    @Override
    public ComputerDTO fetchDTOById(Integer id, Connection connection) throws SQLException, DAOException {
        ComputerDTO computer;

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT + WHERE_ID, true, id);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            computer = ComputerMapper.mapToComputerDTO(resultSet);
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return computer;
    }

    @Override
    public boolean add(Computer computer, Connection connection) throws SQLException, DAOException {

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_INSERT, true, computer.getName(), DAOHelper.convertToDatabaseColumn(computer.getIntroduced()), DAOHelper.convertToDatabaseColumn(computer.getDiscontinued()), computer.getCompany() == null ? null : computer.getCompany().getId())
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException(ERROR_CREATING_THE_COMPUTER);
            }
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return true;
    }

    @Override
    public boolean update(Computer computer, Connection connection) throws SQLException, DAOException {

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_UPDATE, true, computer.getName(), DAOHelper.convertToDatabaseColumn(computer.getIntroduced()), DAOHelper.convertToDatabaseColumn(computer.getDiscontinued()), computer.getCompany() == null ? null : computer.getCompany().getId(), computer.getId())
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException(ERROR_UPDATING_THE_COMPUTER);
            }
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return true;
    }


    @Override
    public boolean delete(int computerId, Connection connection) throws SQLException, DAOException {

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_DELETE, true, computerId)
        ) {
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                throw new DAOException(ERROR_DELETING_THE_COMPUTER);
            }
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return true;
    }

    @Override
    public boolean deleteByCompanyId(Integer id, Connection connection) throws SQLException, DAOException {

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_DELETE_BY_COMPANY_ID, true, id)
        ) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return true;
    }

    @Override
    public List<ComputerDTO> fetchPageDTO(int limit, int offset, String search, ComputerPagerDTO.ORDER order, ComputerPagerDTO.COLUMN column, Connection connection) throws SQLException, DAOException {
        List<ComputerDTO> list;

        String like = search == null ? "%" : "%" + search + "%";

        String orderByQuery = DAOHelper.buildOrderByQuery(order, column);

        try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_SELECT + LIKE + orderByQuery + LIMIT_OFFSET, true, like, like, limit, offset);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = ComputerMapper.mapToComputerDTOList(resultSet);
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info(TRANSACTION_ROLLED_BACK);
            throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
        }

        return list;
    }

    @Override
    public int count(String search, Connection connection) throws SQLException, DAOException {
        int count = 0;

        if (search == null) {
            try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, SQL_COUNT, true);
                 ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    count = resultSet.getInt(TOTAL);
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                connection.rollback();
                throw new SQLException(e);
            }
        } else {
            String like = "%" + search + "%";
            try (PreparedStatement preparedStatement = DAOHelper.initPreparedStatement(connection, COUNT_WITH_SEARCH + LIKE, true, like, like);
                 ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    count = resultSet.getInt(TOTAL);
                }
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info(TRANSACTION_ROLLED_BACK);
                throw new DAOException(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            }
        }

        return count;
    }

}
