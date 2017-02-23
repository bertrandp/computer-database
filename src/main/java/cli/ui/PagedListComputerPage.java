package cli.ui;


import dto.ComputerDTO;
import dto.ComputerPagerDTO;
import service.IComputerService;
import service.impl.ComputerService;
import service.utils.ComputerValidationException;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class PagedListComputerPage {

    /**
     * Display page title.
     */
    static void display() {

        System.out.flush();
        System.out.println("*********************");
        System.out.println("*     Computers     *");
        System.out.println("*********************");
        System.out.println("");

        displayComputerPage(1, 40);
    }

    private static void displayComputerPage(int page, int limit) {
        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");

        IComputerService computerService = new ComputerService();
        try {
            ComputerPagerDTO pager = computerService.getPagedComputerDTOList(String.valueOf(page), String.valueOf(limit));

            for (ComputerDTO computer : (List<ComputerDTO>) pager.getList()) {
                System.out.println("|\t" + computer.getId() + "\t\t" + computer.getName());
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("*********** Page  " + pager.getPage() + "\t/ " + (pager.getCount() / limit + 1) + " ***************");
            System.out.println("*** 1 : Previous      2 : Next        0 : Quit  ***");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch (input.trim()) {
                case "0":
                    MenuPage.display();
                    break;
                case "1":
                    displayComputerPage(pager.getPage() - 1, pager.getLimit());
                    break;
                case "2":
                    displayComputerPage(pager.getPage() + 1, pager.getLimit());
                    break;
                default:
                    displayComputerPage(pager.getPage(), pager.getLimit());
                    break;
            }


        } catch (ComputerValidationException e) {
            e.printStackTrace();
        }

    }

}
