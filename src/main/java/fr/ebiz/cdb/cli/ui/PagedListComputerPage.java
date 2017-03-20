package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
@Component
public class PagedListComputerPage {


    @Autowired
    private IComputerService computerService;

    @Autowired
    private MenuPage menuPage;

    /**
     * Display page title.
     */
    void display() {

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
    private void displayComputerPage(int page, int limit) {
        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");

        ComputerPagerDTO pager = new ComputerPagerDTO.Builder().currentPage(page).limit(limit).build();
        ComputerPagerDTO pageValid = ComputerValidator.validate(pager);
        try {
            pageValid = computerService.fetchComputerList(pageValid);
        } catch (DAOException e) {
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
                menuPage.display();
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
