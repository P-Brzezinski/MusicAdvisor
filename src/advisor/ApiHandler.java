package advisor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHandler {

    public void showNewReleases(){
        String json = createRequest();
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();

        JsonArray asJsonArray = jo.getAsJsonObject("albums").getAsJsonArray("items");

        for (int i = 0; i < asJsonArray.size(); i++) {
            System.out.println();
            NewReleaseRecord record = new NewReleaseRecord();
            String title = asJsonArray.get(i).getAsJsonObject().get("name").getAsString();
            String name = asJsonArray.get(i).getAsJsonObject().getAsJsonArray("artists").get(0).getAsJsonObject().get("name").getAsString();
            String link = asJsonArray.get(i).getAsJsonObject().getAsJsonObject("external_urls").get("spotify").getAsString();
            record.setTitle(title);
            record.setArtist(name);
            record.setUri(URI.create(link));
            System.out.println(record.toString());
        }
    }

    private String createRequest(){
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
