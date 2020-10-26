package advisor.api;

import advisor.http.HTTPServer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiAuthorizationHandler {

    public static String ACCESS_TOKEN;
    public static String ACCOUNT_SERVICE = "https://accounts.spotify.com";

    private String clientId = "b07d74663394474199b86e460e9d01de";
    private String clientSecret = "86a14f7fb3a8489cb87e51a2b6afeed3";
    private String redirectURI = "http://localhost:8081";

    private String authorizationLink = String.format("https://accounts.spotify.com/authorize?client_id=%s&redirect_uri=%s&response_type=code", clientId, redirectURI);
    private String authCode;

    public boolean handleAuthRequest() {
        boolean authorized;
        createContext(HTTPServer.SERVER);
        showLinkForAccessCode();
        waitForAuthCode();
        if (isAccessTokenReceived()) {
            authorized = true;
        } else {
            authorized = false;
        }
        return authorized;
    }

    private void createContext(HttpServer server) {
        HttpContext context = server.createContext("/");
        context.setHandler(this::handleRequestForAuthCode);
    }

    private void showLinkForAccessCode() {
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

    private void waitForAuthCode() {
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

    private boolean isAccessTokenReceived() {
        String json = getResponseWithToken();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        if (jo.has("access_token")) {
            String accessToken = jo.get("access_token").getAsString();
            ACCESS_TOKEN = accessToken;
            return true;
        } else {
            return false;
        }
    }

    private String getResponseWithToken() {
        System.out.println("Making http request for access_token...");

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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        String json = response.body();
        return json;
    }
}

