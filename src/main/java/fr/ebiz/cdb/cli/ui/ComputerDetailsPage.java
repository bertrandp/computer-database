package fr.ebiz.cdb.cli.ui;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
@Component
public class ComputerDetailsPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDetailsPage.class);

    @Autowired
    private IComputerService computerService;

    @Autowired
    private MenuPage menuPage;

    /**
     * Display the details of a computer.
     */
    void display() {


        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        Computer computer;

        computer = computerService.get(Integer.valueOf(id));

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("|\t****\t" + computer.getName() + "\t****");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("|\t" + "Introduced Date" + "\t\tDiscontinued Date" + "\t\tCompany");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("|\t" + computer.getIntroduced() + "\t\t\t" + computer.getDiscontinued() + "\t\t\t\t" + (computer.getCompany() != null ? computer.getCompany().getName() : "null"));
        System.out.println("---------------------------------------------------------------------------------");


        menuPage.display();
    }
}
