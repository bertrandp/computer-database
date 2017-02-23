package cli.ui;


import dto.ComputerDTO;
import service.IComputerService;
import service.impl.ComputerService;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public class ListComputerPage {

    /**
     * Display the page of the list of computer.
     */
    static void display() {

        System.out.flush();
        System.out.println("*********************");
        System.out.println("*     Computers     *");
        System.out.println("*********************");
        System.out.println("");

        IComputerService computerService = new ComputerService();
        List<ComputerDTO> listComputer = computerService.fetchAllDTO();

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName");
        System.out.println("---------------------------------------------------------");
        for (ComputerDTO computer : listComputer) {
            System.out.println("|\t" + computer.getId() + "\t\t" + computer.getName());
        }

        MenuPage.display();
    }
}
