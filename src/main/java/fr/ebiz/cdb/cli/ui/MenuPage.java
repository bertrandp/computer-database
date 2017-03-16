package fr.ebiz.cdb.cli.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
@Component
public class MenuPage {

    private static Logger logger = LoggerFactory.getLogger(MenuPage.class);

    @Autowired
    private ListCompanyPage listCompanyPage;
    @Autowired
    private ComputerDetailsPage computerDetailsPage;
    @Autowired
    private CreateComputerPage createComputerPage;
    @Autowired
    private UpdateComputerPage updateComputerPage;
    @Autowired
    private DeleteComputerPage deleteComputerPage;
    @Autowired
    private PagedListComputerPage pagedListComputerPage;
    @Autowired
    private DeleteCompanyPage deleteCompanyPage;



    /**
     * Display the menu of the CLI.
     */
    void display() {

        System.out.println("");
        System.out.println("*");
        System.out.println("**  Computer Database Application : Menu ");
        System.out.println("*");
        System.out.println("* 2 : List companies ");
        System.out.println("* 3 : Show computer details ");
        System.out.println("* 4 : Create a computer ");
        System.out.println("* 5 : Update a computer ");
        System.out.println("* 6 : Delete a computer ");
        System.out.println("* 7 : List computers (40/page) ");
        System.out.println("* 8 : Delete a company ");
        System.out.println("* 0 : Exit ");
        System.out.println("* ");
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify a number :");
        String option = sc.nextLine();

        switch (option) {
            case "0":
                break;
            case "2":
                listCompanyPage.display(true);
                break;
            case "3":
                computerDetailsPage.display();
                break;
            case "4":
                createComputerPage.display();
                break;
            case "5":
                updateComputerPage.display();
                break;
            case "6":
                deleteComputerPage.display();
                break;
            case "7":
                pagedListComputerPage.display();
                break;
            case "8":
                deleteCompanyPage.display();
                break;
            default:
                logger.error(" *** Error : Wrong entry (the entry must be a number from 0 to 6)");
                display();
                break;
        }


    }
}
