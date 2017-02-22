package dao;

import dao.utils.DAOConfigurationException;
import model.Company;
import model.Computer;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
public class DAOTest {

    @Ignore
    @Test
    public void CompanyDAOTest() {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ICompanyDAO companyDAO = daoFactory.getCompanyDAO();

            // Test : List companies
            List<Company> listCompany = companyDAO.fetchAll();
            System.out.println("Test : List companies");
            System.out.println(listCompany);

        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void CompanyDAOFetchByName() {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ICompanyDAO companyDAO = daoFactory.getCompanyDAO();

            //
            Company company = companyDAO.fetch("IBM");
            System.out.println(company);

        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void ComputerDAOTest() {

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();

        // Test : List computers
        List<Computer> listComputer = computerDAO.fetchAll();
        System.out.println("Test : List computers");
        System.out.println(listComputer);

        // Test : Show computer details
        Computer computer = computerDAO.fetchById(36);
        System.out.println("Test : Show computer details (id = 36)");
        System.out.println(computer);

        // Test : Create a computer
        Computer computerToInsert = new Computer("test", LocalDate.now(), LocalDate.now(), null);
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

    @Ignore
    @Test
    public void PageDAOTest() {

        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();

        // Test : List computers
        List<Computer> listComputer = computerDAO.fetch(20, 20);
        System.out.println("Test : List computers");
        System.out.println(listComputer);

    }

}
