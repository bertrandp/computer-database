package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static fr.ebiz.cdb.service.validation.ComputerValidator.DATE_FORMAT;

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
        String name = writeName(newComputer);
        String introduced = writeIntroduced(newComputer);
        String discontinued = writeDiscontinued(newComputer);
        String companyId = writeCompany(newComputer);

        IComputerService computerService = ComputerService.INSTANCE;
        try {
            computerService.add(name, introduced, discontinued, companyId);
        } catch (InputValidationException | CompanyException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }

    /**
     * Create the name of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeName(Computer newComputer) {
        System.out.println("* Name : ");
        return InputUtils.inputName(newComputer);
    }

    /**
     * Create the introduced date of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeIntroduced(Computer newComputer) {
        System.out.println("* Introduced Date (" + DATE_FORMAT + ") : ");
        System.out.println("* (optional) ");
        return InputUtils.inputIntroducedDate(newComputer);
    }

    /**
     * Create the discontinued date of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeDiscontinued(Computer newComputer) {
        System.out.println("* Discontinued Date (" + DATE_FORMAT + ") : ");
        System.out.println("* (optional) ");
        return InputUtils.inputDiscontinuedDate(newComputer);
    }

    /**
     * Create the company name of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeCompany(Computer newComputer) {
        System.out.println("* Company : ");
        System.out.println("* (optional) ");
        return InputUtils.inputCompanyId(newComputer);
    }
}
