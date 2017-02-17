package ui;

/**
 * Created by ebiz on 15/02/17.
 */
public class UserInterfaceCLI {

    public static void start(){
        init();
        MenuPage.display();
    }

    private static void init() {
        System.out.flush();
        System.out.println("******************************************");
        System.out.println("*     Computer Database Application      *");
        System.out.println("******************************************");
    }

}
