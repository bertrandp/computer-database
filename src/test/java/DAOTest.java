package test.java;

import main.java.dao.impl.CompanyDAO;
import main.java.model.Company;
import org.junit.jupiter.api.Test;
import main.java.dao.DAOConfigurationException;
import main.java.dao.DAOFactory;
import main.java.dao.ICompanyDAO;

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
            List<Company> listCompany = companyDAO.fetchAll();
            System.out.println(listCompany);


        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void CompanyDAOTest2(){
        List<Company> listCompany = CompanyDAO.fetchAll();
        System.out.println(listCompany);
    }*/
}
