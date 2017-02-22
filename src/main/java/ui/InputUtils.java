package ui;


import model.Company;
import model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ICompanyService;
import service.impl.CompanyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static service.utils.ComputerValidator.DATE_FORMAT;

/**
 * Created by ebiz on 16/02/17.
 */
public class InputUtils {

    private static Logger logger = LoggerFactory.getLogger(InputUtils.class);

    /**
     * Check if the company with the given name exist.
     *
     * @param name the name of the company
     * @return true if the company exist
     */
    private static Company companyExists(String name) {
        ICompanyService companyService = new CompanyService();
        return companyService.fetch(name);
    }

    /**
     * Validate the input company name.
     *
     * @param computer the computer to update
     * @return the input
     */
    static String inputCompanyName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":
                break;
            default:
                if (companyExists(input) != null) {
                    return input;
                } else {
                    logger.error("* Error : Invalid Company Name ");
                    CreateComputerPage.writeCompanyName(computer);
                }
                break;
        }
        return null;
    }

    /**
     * Validate the input discontinued date.
     *
     * @param computer the computer to update
     * @return the input
     */
    static String inputDiscontinuedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":
                break;
            default:
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT); // TODO date validation
                    LocalDate date = LocalDate.parse(input, formatter);
                    if (computer.isGreaterThanIntroduced(date)) {
                        return input;
                    } else {
                        logger.error("* Error : Date must be greater than " + computer.getIntroduced());
                        CreateComputerPage.writeDiscontinued(computer);
                    }
                } catch (DateTimeParseException e) {
                    logger.error("* Error : Date is invalid ");
                    CreateComputerPage.writeDiscontinued(computer);
                }
                break;
        }
        return null;
    }

    /**
     * Validate the input introduced date.
     *
     * @param computer the computer to update
     * @return the input
     */
    static String inputIntroducedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        switch (input.trim()) {
            case "":
                break;
            default:
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT); // TODO date validation
                    LocalDate date = LocalDate.parse(input, formatter);
                    return input;
                } catch (DateTimeParseException e) {
                    logger.error("* Error : Date is invalid ");
                    CreateComputerPage.writeIntroduced(computer);
                }
                break;
        }
        return null;
    }

    /**
     * Validate the input name.
     *
     * @param computer the computer to update
     * @return the input
     */
    static String inputName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":
                if (computer.getName() == null) {
                    logger.error("* Error : Name is mandatory ");
                    CreateComputerPage.writeName(computer);
                }
                break;
            default:
                return input;
        }
        return null;
    }
}
