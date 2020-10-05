package advisor;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTP {

    private HttpServer server;

    private String clientId = "b07d74663394474199b86e460e9d01de";
    private String clientSecret = "c5c6a33d2d1e4e60a2ae6466b234099d";
    private String redirectURI = "http://localhost:8081";

    private String authorizationLink = String.format("https://accounts.spotify.com/authorize?client_id=%s&redirect_uri=%s&response_type=code", clientId, redirectURI);
    private String authCode;

    public static String ACCOUNT_SERVICE = "https://accounts.spotify.com";

    public void startServer() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8081), 0);
        server.start();
        createContext(server);
    }

    public void shutdownServer() {
        server.stop(1);
    }

    private void createContext(HttpServer server) {
        HttpContext context = server.createContext("/");
        context.setHandler(this::handleRequestForAuthCode);
    }

    public void showLink() {
        System.out.println("use this link to request the access code:");
        System.out.println(authorizationLink);
    }

    private void handleRequestForAuthCode(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String response;
        if (query != null && query.contains("code")) {
            authCode = query.substring(5);
            response = "Got the code. Return back to your program.";
        } else {
            response = "Authorization code not found. Try again.";
        }
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }

    public void waitForAuthCode() {
        System.out.println("waiting for code...");
        while (authCode == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("code received");
    }

    public void getToken() {
        System.out.println("making http request for access_token...");

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(ACCOUNT_SERVICE + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "&grant_type=authorization_code" +
                                "&code=" + authCode +
                                "&redirect_uri=" + redirectURI +
                                "&client_id=" + clientId +
                                "&client_secret=" + clientSecret))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    }
}

