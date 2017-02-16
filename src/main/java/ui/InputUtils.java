package main.java.ui;

import main.java.model.Company;
import main.java.model.Computer;
import main.java.service.ICompanyService;
import main.java.service.impl.CompanyService;

import java.sql.Date;
import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
public class InputUtils {

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
                System.out.println("* Error : Invalid Company Name ");
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
                Date date = Date.valueOf(input);
                if(computer.isGreaterThanIntroduced(date)) {
                    computer.setDiscontinued(date);
                } else {
                    System.out.println("* Error : Date must be greater than " + computer.getIntroduced());
                    CreateComputerPage.writeDiscontinued(computer);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("* Error : Date is invalid ");
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
                Date date = Date.valueOf(input);
                computer.setIntroduced(date);
            } catch (IllegalArgumentException e) {
                System.out.println("* Error : Date is invalid ");
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
                System.out.println("* Error : Name is mandatory ");
                CreateComputerPage.writeName(computer);
            }
                break;
            default:    computer.setName(input);
                break;
        }
    }
}
