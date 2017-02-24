package fr.ebiz.cdb.cli.ui;

/**
 * Created by ebiz on 15/02/17.
 */
public class UserInterfaceCLI {

    /**
     * Start the CLI.
     */
    public static void start() {
        init();
        MenuPage.display();
    }

    /**
     * Display welcome message.
     */
    private static void init() {
        System.out.flush();
        System.out.println("******************************************");
        System.out.println("*     Computer Database Application      *");
        System.out.println("******************************************");
    }

}
