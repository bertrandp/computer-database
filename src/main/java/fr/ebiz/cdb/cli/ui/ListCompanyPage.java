package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
@Component
public class ListCompanyPage {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private MenuPage menuPage;

    /**
     * Display the page of the list of company.
     *
     * @param withMenu whether the menu should be displayed after the list or not
     */
    void display(boolean withMenu) {
        System.out.println("");
        System.out.println("*********************");
        System.out.println("*     Companies     *");
        System.out.println("*********************");
        System.out.println("");

        List<Company> listCompany = null;
        try {
            listCompany = companyService.fetchAll();
        } catch (DAOException e) {
            System.out.println(e);
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");
        for (Company company : listCompany) {
            System.out.println("|\t" + company.getId() + "\t\t" + company.getName());
        }

        if (withMenu) {
            menuPage.display();
        }
    }


}
