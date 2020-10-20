package advisor;

import java.io.IOException;
import java.util.Scanner;

import static advisor.MainMenuHandler.isAuthorized;
import static advisor.MainMenuHandler.openMenu;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length > 1 && args[0].equals("-access")) {
            AuthorizationHandler.ACCOUNT_SERVICE = args[1];
        }

        if (isAuthorized()) {
            openMenu();
        }
    }
}
