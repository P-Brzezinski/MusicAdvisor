package advisor.api;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequestsHandler {

    public void newReleases(){
        NewReleases.show(createRequest("new-releases"));
    }

    public void featuredPlaylists(){
        FeaturedPlaylists.show(createRequest("featured-playlists"));
    }

    public void categories(){
        Categories.show(createRequest("categories"));
    }

    public void playlistsByCategory(String category){
        String category_id = Categories.getCategoryId(createRequest("categories"), category);
        if (category_id == null){
            System.out.println("Unknown category name");
            System.out.println();
        }else {
            Categories.showByCategory(createRequest(String.format("categories/%s/playlists", category_id)));
        }
    }

    private String createRequest(String request){
        String apiPath = String.format("https://api.spotify.com/v1/browse/%s", request);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + ApiAuthorizationHandler.ACCESS_TOKEN)
                .uri(URI.create(apiPath))
                .GET()
                .build();
        return handleRequest(httpRequest);
    }

    private String handleRequest(HttpRequest request){
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = null;
        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException | NullPointerException e){
            e.printStackTrace();
        }
        return response.body();
    }
}
