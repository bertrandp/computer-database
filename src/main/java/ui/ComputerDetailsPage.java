package ui;

import model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IComputerService;
import service.impl.ComputerService;
import service.utils.ComputerValidationException;

import java.time.LocalDate;
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

        IComputerService computerService = new ComputerService();
        Computer computer;
        try {
            computer = computerService.get(id);

            String introduced;
            String discontinued;
            String companyName;

            LocalDate introducedDate = computer.getIntroduced();
            if (introducedDate == null) {
                introduced = "unknown\t";
            } else {
                introduced = String.valueOf(introducedDate);
            }
            LocalDate discontinuedDate = computer.getDiscontinued();
            if (discontinuedDate == null) {
                discontinued = "unknown\t";
            } else {
                discontinued = String.valueOf(discontinuedDate);
            }
            if (computer.getCompany() != null) {
                companyName = computer.getCompany().getName();
            } else {
                companyName = "unknown";
            }
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|\t****\t" + computer.getName() + "\t****");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|\t" + "Introduced Date" + "\t\tDiscontinued Date" + "\t\tCompany");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|\t" + introduced + "\t\t\t" + discontinued + "\t\t\t\t" + companyName);
            System.out.println("---------------------------------------------------------------------------------");

        } catch (ComputerValidationException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }
}
