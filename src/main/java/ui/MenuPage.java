package main.java.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class MenuPage {

    private static Logger logger = LoggerFactory.getLogger(MenuPage.class);

    static void display() {
        System.out.println("");
        System.out.println("*");
        System.out.println("**  Computer Database Application : Menu ");
        System.out.println("*");
        System.out.println("* 1 : List computers (all entries)");
        System.out.println("* 2 : List companies ");
        System.out.println("* 3 : Show computer details ");
        System.out.println("* 4 : Create a computer ");
        System.out.println("* 5 : Update a computer ");
        System.out.println("* 6 : Delete a computer ");
        System.out.println("* 7 : List computers (40/page) ");
        System.out.println("* 0 : Exit ");
        System.out.println("* ");
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify a number :");
        String option = sc.nextLine();

        switch (option) {
            case "0":   break;
            case "1":   ListComputerPage.display();
                break;
            case "2":   ListCompanyPage.display();
                break;
            case "3":   ComputerDetailsPage.display();
                break;
            case "4":   CreateComputerPage.display();
                break;
            case "5":   UpdateComputerPage.display();
                break;
            case "6":   DeleteComputerPage.display();
                break;
            case "7":   PagedListComputerPage.display();
                break;
            default:    logger.error(" *** Error : Wrong entry (the entry must be a number from 0 to 6)");
                display();
                break;
        }

    }
}
