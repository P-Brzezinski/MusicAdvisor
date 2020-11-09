package advisor;

import advisor.controller.MainMenuController;

import java.io.IOException;

public class Main {

    private static MainMenuController menu = new MainMenuController();

    public static void main(String[] args) throws IOException {

        if (menu.isAuthorized()) {
            menu.openMenu();
        }
    }
}
