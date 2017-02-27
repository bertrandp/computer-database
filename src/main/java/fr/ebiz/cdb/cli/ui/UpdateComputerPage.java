package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            IComputerService computerService = ComputerService.INSTANCE;
            Computer computer;
            try {
                computer = computerService.get(id);

                System.out.println("* Update computer : " + computer.getName());
                String name = updateName(computer);
                String introduced = updateIntroducedDate(computer);
                String discontinued = updateDiscontinuedDate(computer);
                Integer companyId = updateCompanyName(computer);
                computerService = ComputerService.INSTANCE;
                computerService.update(id, name, introduced, discontinued, String.valueOf(companyId));

                MenuPage.display();

            } catch (ComputerException | CompanyException | InputValidationException e) {
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
     * @return the input
     */
    private static Integer updateCompanyName(Computer computer) {
        if (computer.getCompany() != null) {
            System.out.println("* Company Name : " + computer.getCompany().getName());
        } else {
            System.out.println("* Company Name : undefined");
        }
        System.out.println("* Specify the new company name or press Enter ");
        return InputUtils.inputCompanyName(computer);
    }

    /**
     * Display the current discontinued date of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private static String updateDiscontinuedDate(Computer computer) {
        System.out.println("* Discontinued Date : " + computer.getDiscontinued());
        System.out.println("* Specify the new discontinued date or press Enter ");
        return InputUtils.inputDiscontinuedDate(computer);
    }

    /**
     * Display the current introduced date of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private static String updateIntroducedDate(Computer computer) {
        System.out.println("* Introduced Date : " + computer.getIntroduced());
        System.out.println("* Specify the new introduced date or press Enter ");
        return InputUtils.inputIntroducedDate(computer);
    }

    /**
     * Display the current name of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private static String updateName(Computer computer) {
        System.out.println("* Name : " + computer.getName());
        System.out.println("* Specify the new name or press Enter to keep this name ");
        return InputUtils.inputName(computer);
    }

}
