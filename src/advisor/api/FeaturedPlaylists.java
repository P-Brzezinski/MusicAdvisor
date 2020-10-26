package advisor.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FeaturedPlaylists {

    public static void show(String json) {
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        JsonArray asJsonArray = jo.getAsJsonObject("playlists").getAsJsonArray("items");

        for (JsonElement jsonElement : asJsonArray) {
            System.out.println(jsonElement.getAsJsonObject().get("name").getAsString());
            System.out.println(jsonElement.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString());
            System.out.println();
        }
        System.out.println();
    }
}
