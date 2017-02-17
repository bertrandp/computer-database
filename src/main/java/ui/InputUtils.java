package ui;


import model.Company;
import model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ICompanyService;
import service.impl.CompanyService;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class InputUtils {

    private static Logger logger = LoggerFactory.getLogger(InputUtils.class);

    private static boolean companyExists(String input) {
        ICompanyService companyService = new CompanyService();
        return companyService.alreadyExists(input);
    }

    static void inputCompanyName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":    break;
            default:    if(companyExists(input)){
                computer.setCompany(new Company(input));
            } else {
                logger.error("* Error : Invalid Company Name ");
                CreateComputerPage.writeCompanyName(computer);
            }
                break;
        }
    }


    static void inputDiscontinuedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":    break;
            default:    try {
                LocalDate date = LocalDate.parse(input);
                if(computer.isGreaterThanIntroduced(date)) {
                    computer.setDiscontinued(date);
                } else {
                    logger.error("* Error : Date must be greater than " + computer.getIntroduced());
                    CreateComputerPage.writeDiscontinued(computer);
                }
            } catch (IllegalArgumentException e) {
                logger.error("* Error : Date is invalid ");
                CreateComputerPage.writeDiscontinued(computer);
            }
                break;
        }
    }



    static void inputIntroducedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        switch (input.trim()) {
            case "":    break;
            default:    try {
                LocalDate date = LocalDate.parse(input);
                computer.setIntroduced(date);
            } catch (IllegalArgumentException e) {
                logger.error("* Error : Date is invalid ");
                CreateComputerPage.writeIntroduced(computer);
            }
                break;
        }
    }



    static void inputName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":    if(computer.getName() == null) {
                logger.error("* Error : Name is mandatory ");
                CreateComputerPage.writeName(computer);
            }
                break;
            default:    computer.setName(input);
                break;
        }
    }
}
