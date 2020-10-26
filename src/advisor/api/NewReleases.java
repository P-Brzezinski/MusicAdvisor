package advisor.api;

import advisor.api.ApiAuthorizationHandler;
import advisor.model.NewReleaseRecord;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NewReleases {

    protected static void show(String json){
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        JsonArray asJsonArray = jo.getAsJsonObject("albums").getAsJsonArray("items");

        for (int i = 0; i < asJsonArray.size(); i++) {
            NewReleaseRecord record = new NewReleaseRecord();
            String title = asJsonArray.get(i).getAsJsonObject().get("name").getAsString();
            String name = asJsonArray.get(i).getAsJsonObject().getAsJsonArray("artists").get(0).getAsJsonObject().get("name").getAsString();
            String link = asJsonArray.get(i).getAsJsonObject().getAsJsonObject("external_urls").get("spotify").getAsString();
            record.setTitle(title);
            record.setArtist(name);
            record.setUri(URI.create(link));
            System.out.println(record.toString());
            System.out.println();
        }
    }
}
