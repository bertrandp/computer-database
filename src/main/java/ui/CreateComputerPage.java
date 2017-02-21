package ui;


import model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IComputerService;
import service.impl.ComputerService;
import service.utils.ComputerValidationException;

/**
 * Created by ebiz on 16/02/17.
 */
public class CreateComputerPage {

    private static Logger logger = LoggerFactory.getLogger(CreateComputerPage.class);

    /**
     * Display the page to create a computer.
     */
    static void display() {

        Computer newComputer = new Computer();
        System.out.println("* Create a computer ");
        writeName(newComputer);
        writeIntroduced(newComputer);
        writeDiscontinued(newComputer);
        writeCompanyName(newComputer);

        IComputerService computerService = new ComputerService();
        try {
            computerService.add(newComputer);
        } catch (ComputerValidationException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }

    /**
     * Create the name of the computer.
     *
     * @param newComputer computer to create
     */
    static void writeName(Computer newComputer) {
        System.out.println("* Name : ");
        InputUtils.inputName(newComputer);
    }

    /**
     * Create the introduced date of the computer.
     *
     * @param newComputer computer to create
     */
    static void writeIntroduced(Computer newComputer) {
        System.out.println("* Introduced Date (yyyy-mm-dd) : ");
        System.out.println("* (optional) ");
        InputUtils.inputIntroducedDate(newComputer);
    }

    /**
     * Create the discontinued date of the computer.
     *
     * @param newComputer computer to create
     */
    static void writeDiscontinued(Computer newComputer) {
        System.out.println("* Discontinued Date (yyyy-mm-dd) : ");
        System.out.println("* (optional) ");
        InputUtils.inputDiscontinuedDate(newComputer);
    }

    /**
     * Create the company name of the computer.
     *
     * @param newComputer computer to create
     */
    static void writeCompanyName(Computer newComputer) {
        System.out.println("* Company Name : ");
        System.out.println("* (optional) ");
        InputUtils.inputCompanyName(newComputer);
    }
}
