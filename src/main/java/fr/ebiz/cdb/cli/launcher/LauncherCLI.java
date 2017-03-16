package fr.ebiz.cdb.cli.launcher;

import fr.ebiz.cdb.cli.ui.UserInterfaceCLI;
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

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        UserInterfaceCLI userInterfaceCLI = (UserInterfaceCLI) applicationContext.getBean("userInterfaceCLI");

        userInterfaceCLI.start();

    }

}