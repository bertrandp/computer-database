package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.impl.ComputerService;
import fr.ebiz.cdb.service.validation.ComputerValidator;

import java.sql.SQLException;
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

    /**
     * Display paged computer.
     *
     * @param page  the current page
     * @param limit the limit
     */
    private static void displayComputerPage(int page, int limit) {
        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");

        IComputerService computerService = ComputerService.INSTANCE;

        //ComputerPagerDTO pager = computerService.getPagedComputerDTOList(String.valueOf(page), String.valueOf(limit), null);

        ComputerPagerDTO pager = new ComputerPagerDTO.Builder().currentPage(page).limit(limit).build();
        ComputerPagerDTO pageValid = ComputerValidator.validate(pager);
        try {
            pageValid = computerService.fetchComputerList(pageValid);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        }
        for (ComputerDTO computer : (List<ComputerDTO>) pageValid.getList()) {
            System.out.println("|\t" + computer.getId() + "\t\t" + computer.getName());
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("*********** Page  " + pageValid.getCurrentPage() + "\t/ " + (pageValid.getCount() / limit + 1) + " ***************");
        System.out.println("*** 1 : Previous      2 : Next        0 : Quit  ***");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "0":
                MenuPage.display();
                break;
            case "1":
                displayComputerPage(pageValid.getCurrentPage() - 1, pageValid.getLimit());
                break;
            case "2":
                displayComputerPage(pageValid.getCurrentPage() + 1, pageValid.getLimit());
                break;
            default:
                displayComputerPage(pageValid.getCurrentPage(), pageValid.getLimit());
                break;
        }

    }

}
