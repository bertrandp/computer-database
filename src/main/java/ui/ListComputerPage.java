package ui;


import model.Computer;
import service.IComputerService;
import service.impl.ComputerService;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public class ListComputerPage {

    static void display() {

        System.out.flush();
        System.out.println("*********************");
        System.out.println("*     Computers     *");
        System.out.println("*********************");
        System.out.println("");

        IComputerService computerService = new ComputerService();
        List<Computer> listComputer = computerService.fetchAll();

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName" );
        System.out.println("---------------------------------------------------------");
        for(Computer computer : listComputer) {
            System.out.println("|\t" + computer.getId() + "\t\t" + computer.getName());
        }

        MenuPage.display();
    }
}
