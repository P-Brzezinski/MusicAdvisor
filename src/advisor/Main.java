package advisor;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    static HTTP http = new HTTP();

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length > 1 && args[0].equals("-access")) {
            HTTP.ACCOUNT_SERVICE = args[1];
        }

        if (isAuthorized()) {
            openMenu();
        }
    }

    private static boolean isAuthorized() throws IOException {
        String input;
        boolean isAuthorized = false;
        while (!isAuthorized) {
            input = scanner.nextLine();
            switch (input) {
                case "auth":
                    isAuthorized = http.handleAuthRequest();
                    if (isAuthorized){
                        System.out.println("Success!");
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
