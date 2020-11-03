package advisor;

import advisor.api.ApiAuthorizationHandler;
import advisor.menu.MainMenuHandler;

import java.io.IOException;

public class Main {

    private static MainMenuHandler menu = new MainMenuHandler();

    public static void main(String[] args) throws IOException {

        if (menu.isAuthorized()) {
            menu.openMenu();
        }
    }
}
