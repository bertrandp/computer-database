package ui;

import dto.ComputerDTO;
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

        } catch (ComputerValidationException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }
}
