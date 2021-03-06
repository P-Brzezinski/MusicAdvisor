package advisor.controller;

import advisor.controller.api.ApiAuthorizationHandler;
import advisor.http.HTTPServer;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuController {

    private Scanner scanner = new Scanner(System.in);
    private ApiAuthorizationHandler apiAuthorizationHandler = new ApiAuthorizationHandler();
    private ApiController api = new ApiController();

    public boolean isAuthorized() throws IOException {
        HTTPServer.start();
        String input;
        boolean isAuthorized = false;
        while (!isAuthorized) {
            input = scanner.nextLine();
            switch (input) {
                case "auth":
                    isAuthorized = apiAuthorizationHandler.handleAuthRequest();
                    if (isAuthorized) {
                        System.out.println("Success!");
                        HTTPServer.stop();
                    } else {
                        System.out.println("Failed!");
                    }
                    break;
                default:
                    System.out.println("Please, provide access for application.");
            }
        }
        return isAuthorized;
    }

    public void openMenu() {
        String input;
        String category = null;
        do {
            input = scanner.nextLine();
            if (input.contains("playlists")){
                category = input.substring(10);
                input = "playlists";
            }
            switch (input) {
                case "new":
                    api.newReleases();
                    break;
                case "featured":
                    api.featuredPlaylists();
                    break;
                case "categories":
                    api.categories();
                    break;
                case "playlists":
                    api.playlistsByCategory(category);
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    break;
                default:
                    System.out.println("No such option");
            }
        } while (!input.equals("exit"));
    }

    private String getCategory(String input) {
        String[] s = input.split(" ");
        return s[1];
    }
}
