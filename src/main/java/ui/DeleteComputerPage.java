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
public class DeleteComputerPage {

    private static Logger logger = LoggerFactory.getLogger(DeleteComputerPage.class);

    static void display() {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        if(!id.trim().isEmpty()){
            IComputerService computerService = new ComputerService();
            Computer computer;
            try {
                computer = computerService.get(id);
                System.out.println("* Do you really want to delete this computer : " + computer.getName());
                sc = new Scanner(System.in);
                System.out.println("* yes/no :");
                String input = sc.nextLine();
                switch (input) {
                    case "yes": computerService.delete(computer);
                        MenuPage.display();
                        break;
                    default:    MenuPage.display();
                        break;
                }
            } catch (ComputerValidationException e) {
                logger.error("*** Error : " + e.getMessage());
                display();
            }
        } else {
            logger.error(" *** Error : Invalid id");
            display();
        }

    }

}
