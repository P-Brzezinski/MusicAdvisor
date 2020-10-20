package advisor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiHandler {

    public void showNewReleases() {
        getNewReleases();

    }

    //TODO finish iteration thorugh new releases
    public List<String> getNewReleases(){
        String json = createRequest();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();

        System.out.println(jo);
        System.out.println();
        System.out.println(jo.getAsJsonObject("albums").toString());
        System.out.println();
        System.out.println(jo.getAsJsonObject("albums").getAsJsonArray("items").get(0));
        System.out.println();
        System.out.println(jo.getAsJsonObject("albums").getAsJsonArray("items").get(0).getAsJsonObject().getAsJsonArray("artists").get(0).getAsJsonObject().get("name"));

        return null;
    }

    public String createRequest(){
        String apiPath = "https://api.spotify.com/v1/browse/new-releases";
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + AuthorizationHandler.ACCESS_TOKEN)
                .uri(URI.create(apiPath))
                .GET()
                .build();
        return handleRequest(request);
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
