package main.java.dao.impl;

import main.java.dao.DAOException;
import main.java.dao.DAOFactory;
import main.java.dao.ICompanyDAO;
import main.java.dao.IComputerDAO;
import main.java.dao.utils.DAOHelper;
import main.java.model.Company;
import main.java.model.Computer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 15/02/17.
 */
public class ComputerDAO implements IComputerDAO {

    private DAOFactory daoFactory;
    private static final String SQL_SELECT = "SELECT * FROM computer";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id ) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM computer WHERE id = ?";

    public ComputerDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Computer> fetchAll() {
        List<Computer> list;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT, true);
            resultSet = preparedStatement.executeQuery();
            list = getComputerList(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection(resultSet, preparedStatement, connection);
        }

        return list;
    }

    @Override
    public Computer fetchById(int id) {
        Computer computer;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_SELECT_BY_ID, true, id);
            resultSet = preparedStatement.executeQuery();
            computer = getComputer(resultSet);
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection(resultSet, preparedStatement, connection);
        }

        return computer;
    }

    @Override
    public boolean add(Computer computer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer companyId = null ;

        if(computer.getCompany() != null) {
            ICompanyDAO companyDAO = daoFactory.CompanyDAO();
            Company company = companyDAO.fetch(computer.getCompany().getName());
            if(company == null) {
                throw new DAOException( "Failed to create computer : " + computer.getName() + ". Company name must be an existing company");
            } else {
                companyId = company.getId();
            }
        }

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_INSERT, true, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), companyId);
            int status = preparedStatement.executeUpdate();

            if(status == 0){
                throw new DAOException( "Failed to create computer : " + computer);
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection( preparedStatement, connection);
        }

        return true;
    }

    @Override
    public boolean update(Computer computer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_UPDATE, true, computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), computer.getId());
            int status = preparedStatement.executeUpdate();

            if(status == 0){
                throw new DAOException( "Failed to update computer : " + computer );
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection( preparedStatement, connection);
        }

        return true;
    }

    @Override
    public boolean delete(Computer computer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection= daoFactory.getConnection();
            preparedStatement = DAOHelper.initPreparedStatement( connection, SQL_DELETE, true, computer.getId());
            int status = preparedStatement.executeUpdate();

            if(status == 0){
                throw new DAOException( "Failed to delete computer : " + computer );
            }
        } catch (SQLException e) {
            throw new DAOException( e );
        } finally {
            DAOHelper.closeConnection( preparedStatement, connection);
        }

        return true;
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
            computer.setIntroduced(resultSet.getDate("introduced"));
            computer.setDiscontinued(resultSet.getDate("discontinued"));
            ICompanyDAO companyDAO = daoFactory.CompanyDAO();
            Company company = companyDAO.fetch(resultSet.getInt("company_id"));
            computer.setCompany(company);
            return computer;
        }
        return null;
    }
}
