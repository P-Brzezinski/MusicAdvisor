package advisor.menu;

import advisor.api.RequestsHandler;
import advisor.api.requests.NewReleaseRequest;
import advisor.api.AuthorizationHandler;
import advisor.http.HTTPServer;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuHandler {

    private Scanner scanner = new Scanner(System.in);
    private AuthorizationHandler authorizationHandler = new AuthorizationHandler();
    public RequestsHandler api = new RequestsHandler();

    public boolean isAuthorized() throws IOException {
        HTTPServer.start();
        String input;
        boolean isAuthorized = false;
        while (!isAuthorized) {
            input = scanner.nextLine();
            switch (input) {
                case "auth":
                    isAuthorized = authorizationHandler.handleAuthRequest();
                    if (isAuthorized){
                        System.out.println("Success!");
                        HTTPServer.stop();
                    }else {
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
                    api.showNewReleases();
                    break;
                case "featured":
                    System.out.println("---FEATURED---");
                    System.out.println("Mellow Morning");
                    break;
                case "categories":
                    System.out.println("---CATEGORIES---");
                    System.out.println("Pop");
                    System.out.println("Mood");
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
