package fr.ebiz.cdb.cli.ui;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerDetailsPage {

    private static Logger logger = LoggerFactory.getLogger(ComputerDetailsPage.class);

    /**
     * Display the details of a computer.
     */
    static void display() {

        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        IComputerService computerService = ComputerService.INSTANCE;
        ComputerDTO computer;
        try {
            computer = computerService.getDTO(id);

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|\t****\t" + computer.getName() + "\t****");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|\t" + "Introduced Date" + "\t\tDiscontinued Date" + "\t\tCompany");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|\t" + computer.getIntroduced() + "\t\t\t" + computer.getDiscontinued() + "\t\t\t\t" + computer.getName());
            System.out.println("---------------------------------------------------------------------------------");

        } catch (ComputerException | InputValidationException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }
}
