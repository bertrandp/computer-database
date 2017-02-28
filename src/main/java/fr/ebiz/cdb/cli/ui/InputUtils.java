package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static fr.ebiz.cdb.service.validation.ComputerValidator.DATE_FORMAT;

/**
 * Created by ebiz on 16/02/17.
 */
public class InputUtils {

    private static Logger logger = LoggerFactory.getLogger(InputUtils.class);

    /**
     * Validate the input company name.
     *
     * @param computer the computer to update
     * @return the input
     */
    static String inputCompanyId(Computer computer) {
        ListCompanyPage.display(false);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":
                CreateComputerPage.writeCompany(computer);
                break;
            default:
                return input;
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
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
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
