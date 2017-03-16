package fr.ebiz.cdb.cli.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ebiz on 15/02/17.
 */
@Component
public class UserInterfaceCLI {

    @Autowired
    private MenuPage menuPage;

    /**
     * Start the CLI.
     */
    public void start() {
        init();
        menuPage.display();
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
