package advisor;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static String clientId = "b07d74663394474199b86e460e9d01de";
    private static String redirectURI = "http://localhost:8080";
    private static String authorizationLink = String.format("https://accounts.spotify.com/authorize?client_id=%s&redirect_uri=%s&response_type=code", clientId, redirectURI);

    public static void main(String[] args) {

        if (isAuthorized()) {
            openMenu();
        }
    }

    private static boolean isAuthorized() {
        String input;
        boolean isAuthorized = false;
        while (!isAuthorized) {
            input = scanner.nextLine();
            switch (input) {
                case "auth":
                    System.out.println(authorizationLink);
                    System.out.println("---SUCCESS---");
                    isAuthorized = true;
                    break;
                default:
                    System.out.println("Please provide access for application.");
            }
        }
        return isAuthorized;
    }

    private static void openMenu() {
        String input;
        do {
            input = scanner.nextLine();
            switch (input) {
                case "new":
                    System.out.println("---NEW RELEASES---");
                    System.out.println("Mountains [Sia, Diplo, Labrinth]");
                    System.out.println("Runaway [Lil Peep]");
                    System.out.println("The Greatest Show [Panic! At The Disco]");
                    System.out.println("All Out Life [Slipknot]");
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
