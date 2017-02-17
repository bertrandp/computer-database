package dao.impl;

import dao.DAOFactory;
import dao.ICompanyDAO;
import dao.IComputerDAO;
import dao.utils.DAOException;
import dao.utils.DAOHelper;
import model.Company;
import model.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public class ComputerDAO implements IComputerDAO {

    private DAOFactory daoFactory;
    private static final String SQL_SELECT = "SELECT * FROM computer";
    private static final String SQL_COUNT = "SELECT count(*) AS total FROM computer";
    private static final String SQL_SELECT_PAGE = "SELECT * FROM computer LIMIT ? OFFSET ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id ) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";

    public ComputerDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> fetchAll() {
        List<Computer> list;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT, true);
             ResultSet resultSet = preparedStatement.executeQuery()
            ) {
            list = getComputerList(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Computer fetchById(int id) {
        Computer computer;

        try (   Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT_BY_ID, true, id);
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {
            computer = getComputer(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return computer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Computer computer) {
        Integer companyId = validateCompany(computer);

        try (   Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_INSERT, true, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), companyId);
            ) {
            int status = preparedStatement.executeUpdate();
            if(status == 0){
                throw new DAOException( "Failed to create computer : " + computer);
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Computer computer) {
        Integer companyId = validateCompany(computer);

        try (   Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_UPDATE, true, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), companyId, computer.getId());
            ) {
            int status = preparedStatement.executeUpdate();
            if(status == 0){
                throw new DAOException( "Failed to update computer : " + computer );
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return true;
    }

    private Integer validateCompany(Computer computer) {
        Integer companyId = null;
        if(computer.getCompany() != null) {
            ICompanyDAO companyDAO = daoFactory.CompanyDAO();
            Company company = companyDAO.fetch(computer.getCompany().getName());
            if(company == null) {
                throw new DAOException( "Failed to create computer : " + computer.getName() + ". Company name must be an existing company");
            } else {
                companyId = company.getId();
            }
        }
        return companyId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Computer computer) {

        try (   Connection connection= daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_DELETE, true, computer.getId());
            ) {
            int status = preparedStatement.executeUpdate();
            if(status == 0){
                throw new DAOException( "Failed to delete computer : " + computer );
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Computer> fetch(int limit, int offset) {
        List<Computer> list;

        try (   Connection connection= daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT_PAGE, true, limit, offset);
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {
            list = getComputerList(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return list;
    }

    @Override
    public int count() {
        int count = 0;

        try (   Connection connection= daoFactory.getConnection();
                PreparedStatement preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_COUNT, true);
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {
            if( resultSet.next() ) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return count;
    }


    private List<Computer> getComputerList(ResultSet resultSet) throws SQLException {
        List<Computer> list = new ArrayList<>();
        while(resultSet.next()){
            Computer computer = new Computer();
            computer.setId(resultSet.getInt("id"));
            computer.setName(resultSet.getString("name"));
            list.add(computer);
        }
        return list;
    }


    private Computer getComputer(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        if(resultSet.next()){
            computer.setId(resultSet.getInt("id"));
            computer.setName(resultSet.getString("name"));
            Date inputIntroduced = resultSet.getDate("introduced");
            if(inputIntroduced != null) {
                computer.setIntroduced(inputIntroduced.toLocalDate());
            } else {
                computer.setIntroduced(null);
            }
            Date inputDiscontinued = resultSet.getDate("discontinued");
            if(inputDiscontinued != null) {
                computer.setDiscontinued(inputDiscontinued.toLocalDate());
            } else {
                computer.setDiscontinued(null);
            }
            ICompanyDAO companyDAO = daoFactory.CompanyDAO();
            Company company = companyDAO.fetch(resultSet.getInt("company_id"));
            computer.setCompany(company);
            return computer;
        }
        return null;
    }
}
