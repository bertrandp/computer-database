package main.java.ui;

import main.java.model.Computer;
import main.java.service.IComputerService;
import main.java.service.impl.ComputerService;
import main.java.service.impl.ComputerValidationException;

import java.sql.Date;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerDetailsPage {

    static void display() {

        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        IComputerService computerService = new ComputerService();
        Computer computer = null;
        try {
            computer = computerService.get(id);

            String introduced;
            String discontinued;
            String companyName;

            Date introducedDate = computer.getIntroduced();
            if(introducedDate==null) {
                introduced = "unknown\t";
            } else {
                introduced = String.valueOf(introducedDate);
            }
            Date discontinuedDate = computer.getDiscontinued();
            if(discontinuedDate==null) {
                discontinued = "unknown\t";
            } else {
                discontinued = String.valueOf(discontinuedDate);
            }
            if(computer.getCompany() != null) {
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
            System.out.println("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }
}
