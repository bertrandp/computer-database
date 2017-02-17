package ui;


import model.Computer;
import model.ComputerPager;
import model.Pager;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class PagedListComputerPage {

    static void display() {

        System.out.flush();
        System.out.println("*********************");
        System.out.println("*     Computers     *");
        System.out.println("*********************");
        System.out.println("");

        Pager computerPager = new ComputerPager();
        displayComputerPage(computerPager);
    }

    private static void displayComputerPage(Pager computerPager) {

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");
        for (Computer computer : (List<Computer>) computerPager.getList()) {
            System.out.println("|\t" + computer.getId() + "\t\t" + computer.getName());
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("*********** Pager " + computerPager.getIndex() + "\t/ " + computerPager.countPages() + " ***************");
        System.out.println("*** 1 : Previous      2 : Next        0 : Quit  ***");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "0":
                MenuPage.display();
                break;
            case "1":
                if (computerPager.hasPrevious()) {
                    computerPager.previous();
                }
                displayComputerPage(computerPager);
                break;
            case "2":
                if (computerPager.hasNext()) {
                    computerPager.next();
                }
                displayComputerPage(computerPager);
                break;
            default:
                displayComputerPage(computerPager);
                break;
        }
    }


}
