package test.java;

import main.java.dao.IComputerDAO;
import main.java.dao.impl.CompanyDAO;
import main.java.model.Company;
import main.java.model.Computer;
import org.junit.jupiter.api.Test;
import main.java.dao.DAOConfigurationException;
import main.java.dao.DAOFactory;
import main.java.dao.ICompanyDAO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class DAOTest {

    @Test
    public void CompanyDAOTest() {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ICompanyDAO companyDAO = daoFactory.CompanyDAO();

            // Test : List companies
            List<Company> listCompany = companyDAO.fetchAll();
            System.out.println("Test : List companies");
            System.out.println(listCompany);

        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CompanyDAOFetchByName() {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ICompanyDAO companyDAO = daoFactory.CompanyDAO();

            //
            Company company = companyDAO.fetch("IBM");
            System.out.println(company);

        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ComputerDAOTest() {

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();

        // Test : List computers
        List<Computer> listComputer = computerDAO.fetchAll();
        System.out.println("Test : List computers");
        System.out.println(listComputer);

        // Test : Show computer details
        Computer computer = computerDAO.fetchById(36);
        System.out.println("Test : Show computer details (id = 36)");
        System.out.println(computer);

        // Test : Create a computer
        Computer computerToInsert = new Computer("test", new Date(Calendar.getInstance().getTimeInMillis()),  new Date(Calendar.getInstance().getTimeInMillis()), null);
        System.out.println("Test : Create a computer");
        listComputer = computerDAO.fetchAll();
        System.out.println("List computers : ");
        System.out.println(listComputer);
        System.out.println("Add computer ");
        computerDAO.add(computerToInsert);
        listComputer = computerDAO.fetchAll();
        System.out.println("List computers : ");
        System.out.println(listComputer);

        // Test : Update a computer
        computer = computerDAO.fetchById(409);
        System.out.println("Test : Update a computer (id = 409)");
        System.out.println("Show computer details (id = 409)");
        System.out.println(computer);
        computer.setName("Test Name");
        computerDAO.update(computer);
        System.out.println("Show computer details (id = 409)");
        System.out.println(computer);

        // Test : Delete a computer
        /*computer = computerDAO.fetchById(418);
        System.out.println("Test : Delete a computer (id = 577)");
        System.out.println("Show computer details (id = 577)");
        System.out.println(computer);
        computerDAO.delete(computer);
        computer = computerDAO.fetchById(577);
        System.out.println("Show computer details (id = 577)");
        System.out.println(computer);*/
    }

}
