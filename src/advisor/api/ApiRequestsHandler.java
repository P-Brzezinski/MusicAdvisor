package advisor.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequestsHandler {

    private static final String NEW_RELEASES = "new-releases";
    private static final String FEATURED_PLAYLISTS = "featured-playlists";
    private static final String CATEGORIES = "categories";

    public void getNewReleases(){
        NewReleases.show(createRequest(NEW_RELEASES));
    }

    public void getFeaturedPlaylists(){
        FeaturedPlaylists.show(createRequest(FEATURED_PLAYLISTS));
    }

    public void getCategories(){
        Categories.show(createRequest(CATEGORIES));
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
