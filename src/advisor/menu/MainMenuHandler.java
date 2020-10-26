package advisor.menu;

import advisor.api.ApiRequestsHandler;
import advisor.api.ApiAuthorizationHandler;
import advisor.http.HTTPServer;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuHandler {

    private Scanner scanner = new Scanner(System.in);
    private ApiAuthorizationHandler apiAuthorizationHandler = new ApiAuthorizationHandler();
    private ApiRequestsHandler api = new ApiRequestsHandler();

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
        do {
            input = scanner.nextLine();
            switch (input) {
                case "new":
                    api.getNewReleases();
                    break;
                case "featured":
                    api.getFeaturedPlaylists();
                    break;
                case "categories":
                    api.getCategories();
                    break;
                case "playlists Mood":
                    System.out.println("---MOOD PLAYLISTS---");
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    break;
                default:
                    System.out.println("No such option");
            }
        } while (!input.equals("exit"));
    }
}
