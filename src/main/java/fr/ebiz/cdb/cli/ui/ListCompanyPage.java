package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.impl.CompanyService;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public class ListCompanyPage {

    /**
     * Display the page of the list of company.
     *
     * @param withMenu whether the menu should be displayed after the list or not
     */
    static void display(boolean withMenu) {
        System.out.println("");
        System.out.println("*********************");
        System.out.println("*     Companies     *");
        System.out.println("*********************");
        System.out.println("");

        ICompanyService companyService = CompanyService.INSTANCE;
        List<Company> listCompany = companyService.fetchAll();

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");
        for (Company company : listCompany) {
            System.out.println("|\t" + company.getId() + "\t\t" + company.getName());
        }

        if (withMenu) {
            MenuPage.display();
        }
    }


}
