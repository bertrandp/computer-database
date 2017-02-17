package ui;


import model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IComputerService;
import service.impl.ComputerService;
import service.impl.ComputerValidationException;

/**
 * Created by ebiz on 16/02/17.
 */
public class CreateComputerPage {

    private static Logger logger = LoggerFactory.getLogger(CreateComputerPage.class);

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

    static void writeName(Computer newComputer) {
        System.out.println("* Name : ");
        InputUtils.inputName(newComputer);
    }

    static void writeIntroduced(Computer newComputer) {
        System.out.println("* Introduced Date (yyyy-mm-dd) : ");
        System.out.println("* (optional) ");
        InputUtils.inputIntroducedDate(newComputer);
    }

    static void writeDiscontinued(Computer newComputer) {
        System.out.println("* Discontinued Date (yyyy-mm-dd) : ");
        System.out.println("* (optional) ");
        InputUtils.inputDiscontinuedDate(newComputer);
    }

    static void writeCompanyName(Computer newComputer) {
        System.out.println("* Company Name : ");
        System.out.println("* (optional) ");
        InputUtils.inputCompanyName(newComputer);
    }
}
