package fr.ebiz.cdb.console.launcher;

import fr.ebiz.cdb.console.ui.UserInterfaceCLI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bpestre on 17/02/17.
 */
public class LauncherCLI {

    /**
     * Entry point of the CLI.
     *
     * @param args parameters
     */
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-console.xml");

        UserInterfaceCLI userInterfaceCLI = (UserInterfaceCLI) applicationContext.getBean("userInterfaceCLI");

        userInterfaceCLI.start();

    }

}