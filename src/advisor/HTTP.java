package advisor;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTP {

    private HttpServer server;

    public void startServer() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8081), 0);
        server.start();

        HttpContext context = server.createContext("/");
        context.setHandler(HTTP::handleRequest);
    }

    public String getAuthCode(String redirectURI) throws IOException, InterruptedException {
        String authCode = "";

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(redirectURI))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        do {
            Thread.sleep(10);
            if (response.uri().getQuery() != null){
                authCode = response.uri().getQuery();
            }
            System.out.println(authCode);
        }while (authCode.equals(""));
        System.out.println("code received");
        System.out.println(authCode);

//        String[] split = response.uri().getQuery().split("=");
//        authCode = split[1];
//        System.out.println(authCode);
        return authCode;
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String response;
//        printRequestInfo(exchange);
        if (requestURI.toString().equals("/?error=access_denied")) {
            response = "Authorization code not found. Try again.";
        } else {
            response = "Got the code. Return back to your program.";
        }
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void printRequestInfo(HttpExchange exchange) {
        System.out.println("-- headers --");
        Headers requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);

        System.out.println("-- principle --");
        HttpPrincipal principal = exchange.getPrincipal();
        System.out.println(principal);

        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);

        System.out.println("-- query --");
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        System.out.println(query);
    }

}

