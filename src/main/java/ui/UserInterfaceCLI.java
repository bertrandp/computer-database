package main.java.ui;

import main.java.model.Company;
import main.java.model.Computer;
import main.java.service.ICompanyService;
import main.java.service.IComputerService;
import main.java.service.impl.CompanyService;
import main.java.service.impl.ComputerService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ebiz on 15/02/17.
 */
public class UserInterfaceCLI {

    private static boolean exit = false;

    public static void start(){

        init();
        menu();

    }

    private static void menu() {
        System.out.println("");
        System.out.println("*");
        System.out.println("**  Computer Database Application : Menu ");
        System.out.println("*");
        System.out.println("* 1 : List computers ");
        System.out.println("* 2 : List companies ");
        System.out.println("* 3 : Show computer details ");
        System.out.println("* 4 : Create a computer ");
        System.out.println("* 5 : Update a computer ");
        System.out.println("* 6 : Delete a computer ");
        System.out.println("* 0 : Exit ");
        System.out.println("* ");
        Scanner sc = new Scanner(System.in);
        System.out.println("* Enter a number :");
        String option = sc.nextLine();

        switch (option) {
            case "0":   exit = true;
                        break;
            case "1":   listComputers();
                        break;
            case "2":   listCompanies();
                        break;
            case "3":   computerDetails();
                        break;
            case "4":   createComputer();
                        break;
            case "5":   updateComputer();
                        break;
            case "6":   deleteComputer();
                        break;
            default:    System.out.println(" *** Error : Wrong entry (the entry must be a number from 0 to 6)");
                        break;
        }

    }

    private static void listCompanies() {
        System.out.println("");
        System.out.println("*********************");
        System.out.println("*     Companies     *");
        System.out.println("*********************");
        System.out.println("");

        ICompanyService companyService = new CompanyService();
        List<Company> listCompany = companyService.fetchAll();

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName" );
        System.out.println("---------------------------------------------------------");
        for(Company company : listCompany) {
            System.out.println("|\t" + company.getId() + "\t\t" + company.getName());
        }

        menu();
    }

    private static void listComputers() {

        System.out.flush();
        System.out.println("*********************");
        System.out.println("*     Computers     *");
        System.out.println("*********************");
        System.out.println("");

        IComputerService computerService = new ComputerService();
        List<Computer> listComputer = computerService.fetchAll();

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t" + "n°" + "\t\tName" );
        System.out.println("---------------------------------------------------------");
        for(Computer computer : listComputer) {
            System.out.println("|\t" + computer.getId() + "\t\t" + computer.getName());
        }

        menu();
    }

    private static void computerDetails() {

        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        IComputerService computerService = new ComputerService();
        Computer computer = computerService.get(id);
        if(computer != null) {

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

        } else {
            System.out.println(" *** Error : Computer n°" + id + " not found.");
        }

        menu();
    }

    private static void createComputer() {

        Computer newComputer = new Computer();

        Scanner sc = new Scanner(System.in);
        System.out.println("* Create a computer ");
        writeName(newComputer);
        writeIntroduced(newComputer);
        writeDiscontinued(newComputer);
        writeCompanyName(newComputer);

        IComputerService computerService = new ComputerService();
        computerService.add(newComputer);

        menu();
    }


    private static void writeName(Computer newComputer) {
        System.out.println("* Name : ");
        inputName(newComputer);
    }

    private static void writeIntroduced(Computer newComputer) {
        System.out.println("* Introduced Date (yyyy-mm-dd) : ");
        System.out.println("* (optional) ");
        inputIntroducedDate(newComputer);
    }

    private static void writeDiscontinued(Computer newComputer) {
        System.out.println("* Discontinued Date (yyyy-mm-dd) : ");
        System.out.println("* (optional) ");
        inputDiscontinuedDate(newComputer);
    }

    private static void writeCompanyName(Computer newComputer) {
        System.out.println("* Company Name : ");
        System.out.println("* (optional) ");
        inputCompanyName(newComputer);
    }

    private static boolean companyExists(String input) {
        ICompanyService companyService = new CompanyService();
        return companyService.alreadyExists(input);
    }


    private static void deleteComputer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        if(!id.trim().isEmpty()){
            IComputerService computerService = new ComputerService();
            Computer computer = computerService.get(id);
            if(computer != null) {
                System.out.println("* Do you really want to delete this computer : " + computer.getName());
                sc = new Scanner(System.in);
                System.out.println("* yes/no :");
                String input = sc.nextLine();
                switch (input) {
                    case "yes": computerService.delete(computer);
                                menu();
                                break;
                    default:    menu();
                                break;
                }

            } else {
                System.out.println(" *** Error : Computer n°" + id + " not found.");
                deleteComputer();
            }
        } else {
            System.out.println(" *** Error : Invalid id");
            deleteComputer();
        }

    }

    private static void updateComputer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        if(!id.trim().isEmpty()){
            IComputerService computerService = new ComputerService();
            Computer computer = computerService.get(id);
            if(computer != null) {
                System.out.println("* Update computer : " + computer.getName());
                updateName(computer);
                updateIntroducedDate(computer);
                updateDiscontinuedDate(computer);
                updateCompanyName(computer);
                computerService = new ComputerService();
                computerService.update(computer);
                menu();

            } else {
                System.out.println(" *** Error : Computer n°" + id + " not found.");
                updateComputer();
            }
        } else {
            System.out.println(" *** Error : Invalid id");
            updateComputer();
        }

    }

    private static void updateCompanyName(Computer computer) {
        if(computer.getCompany() != null) {
            System.out.println("* Company Name : " + computer.getCompany().getName());
        } else {
            System.out.println("* Company Name : undefined");
        }
        System.out.println("* Specify the new company name or press Enter ");
        inputCompanyName(computer);
    }

    private static void inputCompanyName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":    break;
            default:    if(companyExists(input)){
                            computer.setCompany(new Company(input));
                        } else {
                            System.out.println("* Error : Invalid Company Name ");
                            writeCompanyName(computer);
                        }
                        break;
        }
    }

    private static void updateDiscontinuedDate(Computer computer) {
        System.out.println("* Discontinued Date : " + computer.getDiscontinued());
        System.out.println("* Specify the new discontinued date or press Enter ");
        inputDiscontinuedDate(computer);
    }

    private static void inputDiscontinuedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":    break;
            default:    try {
                            Date date = Date.valueOf(input);
                            computer.setDiscontinued(date);
                        } catch (IllegalArgumentException e) {
                            System.out.println("* Error : Date is invalid ");
                            writeDiscontinued(computer);
                        }
                        break;
        }
    }

    private static void updateIntroducedDate(Computer computer) {
        System.out.println("* Introduced Date : " + computer.getIntroduced());
        System.out.println("* Specify the new introduced date or press Enter ");
        inputIntroducedDate(computer);
    }

    private static void inputIntroducedDate(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        switch (input.trim()) {
            case "":    break;
            default:    try {
                            Date date = Date.valueOf(input);
                            computer.setIntroduced(date);
                        } catch (IllegalArgumentException e) {
                            System.out.println("* Error : Date is invalid ");
                            writeIntroduced(computer);
                        }
                        break;
        }
    }

    private static void updateName(Computer computer) {
        System.out.println("* Name : " + computer.getName());
        System.out.println("* Specify the new name or press Enter to keep this name ");
        inputName(computer);
    }

    private static void inputName(Computer computer) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input.trim()) {
            case "":    if(computer.getName() == null) {
                            System.out.println("* Error : Name is mandatory ");
                            writeName(computer);
                        }
                        break;
            default:    computer.setName(input);
                break;
        }
    }


    private static void init() {

        System.out.flush();
        System.out.println("******************************************");
        System.out.println("*     Computer Database Application      *");
        System.out.println("******************************************");

    }
}
