package ui;


import model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IComputerService;
import service.impl.ComputerService;
import service.impl.ComputerValidationException;

import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class UpdateComputerPage {

    private static Logger logger = LoggerFactory.getLogger(UpdateComputerPage.class);

    /**
     * Display the page to update a computer.
     */
    static void display() {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        if (!id.trim().isEmpty()) {
            IComputerService computerService = new ComputerService();
            Computer computer;
            try {
                computer = computerService.get(id);

                System.out.println("* Update computer : " + computer.getName());
                updateName(computer);
                updateIntroducedDate(computer);
                updateDiscontinuedDate(computer);
                updateCompanyName(computer);
                computerService = new ComputerService();
                computerService.update(computer);

                MenuPage.display();

            } catch (ComputerValidationException e) {
                logger.error("*** Error : " + e.getMessage());
                MenuPage.display();
            }
        } else {
            logger.error(" *** Error : Invalid id");
            display();
        }

    }

    /**
     * Display the current company name of the given computer and ask for an update.
     *
     * @param computer the computer to update
     */
    private static void updateCompanyName(Computer computer) {
        if (computer.getCompany() != null) {
            System.out.println("* Company Name : " + computer.getCompany().getName());
        } else {
            System.out.println("* Company Name : undefined");
        }
        System.out.println("* Specify the new company name or press Enter ");
        InputUtils.inputCompanyName(computer);
    }

    /**
     * Display the current discontinued date of the given computer and ask for an update.
     *
     * @param computer the computer to update
     */
    private static void updateDiscontinuedDate(Computer computer) {
        System.out.println("* Discontinued Date : " + computer.getDiscontinued());
        System.out.println("* Specify the new discontinued date or press Enter ");
        InputUtils.inputDiscontinuedDate(computer);
    }

    /**
     * Display the current introduced date of the given computer and ask for an update.
     *
     * @param computer the computer to update
     */
    private static void updateIntroducedDate(Computer computer) {
        System.out.println("* Introduced Date : " + computer.getIntroduced());
        System.out.println("* Specify the new introduced date or press Enter ");
        InputUtils.inputIntroducedDate(computer);
    }

    /**
     * Display the current name of the given computer and ask for an update.
     *
     * @param computer the computer to update
     */
    private static void updateName(Computer computer) {
        System.out.println("* Name : " + computer.getName());
        System.out.println("* Specify the new name or press Enter to keep this name ");
        InputUtils.inputName(computer);
    }

}
