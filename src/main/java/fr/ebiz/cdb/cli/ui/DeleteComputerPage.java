package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.impl.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class DeleteComputerPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputerPage.class);

    /**
     * Display the page to delete a computer.
     */
    static void display() {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        if (!id.trim().isEmpty()) {
            IComputerService computerService = ComputerService.INSTANCE;
            ComputerDTO computer;
            try {
                computer = computerService.getDTO(Integer.valueOf(id));
                System.out.println("* Do you really want to delete this computer : " + computer.getName());
                sc = new Scanner(System.in);
                System.out.println("* yes/no :");
                String input = sc.nextLine();
                switch (input) {
                    case "yes":

                        computerService.delete(new ArrayList<>(computer.getId()));
                        MenuPage.display();
                        break;
                    default:
                        MenuPage.display();
                        break;
                }
            } catch (DAOException e) {
                LOGGER.error(e.getMessage());
                // TODO handle exception
            }
        } else {
            LOGGER.error(" *** Error : Invalid id");
            display();
        }

    }

}
