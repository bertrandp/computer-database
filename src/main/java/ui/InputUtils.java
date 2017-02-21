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
    private static boolean companyExists(String name) {
        ICompanyService companyService = new CompanyService();
        return companyService.nameAlreadyExists(name);
    }

    /**
     * Validate the input company name.
     *
     * @param computer the computer to update
     */
    static void inputCompanyName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":
                break;
            default:
                if (companyExists(input)) {
                    computer.setCompany(new Company(input));
                } else {
                    logger.error("* Error : Invalid Company Name ");
                    CreateComputerPage.writeCompanyName(computer);
                }
                break;
        }
    }

    /**
     * Validate the input discontinued date.
     *
     * @param computer the computer to update
     */
    static void inputDiscontinuedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":
                break;
            default:
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // TODO date validation
                    LocalDate date = LocalDate.parse(input, formatter);
                    if (computer.isGreaterThanIntroduced(date)) {
                        computer.setDiscontinued(date);
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
    }

    /**
     * Validate the input introduced date.
     *
     * @param computer the computer to update
     */
    static void inputIntroducedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        switch (input.trim()) {
            case "":
                break;
            default:
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // TODO date validation
                    LocalDate date = LocalDate.parse(input, formatter);
                    computer.setIntroduced(date);
                } catch (DateTimeParseException e) {
                    logger.error("* Error : Date is invalid ");
                    CreateComputerPage.writeIntroduced(computer);
                }
                break;
        }
    }

    /**
     * Validate the input name.
     *
     * @param computer the computer to update
     */
    static void inputName(Computer computer) {
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
                computer.setName(input);
                break;
        }
    }
}
