package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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
        }while (!input.equals("exit"));
    }
}
