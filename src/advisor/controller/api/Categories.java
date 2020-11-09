package advisor.controller.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Categories {

    public static void show(String json) {
        JsonArray allCategories = getAllCategories(json);
        for (JsonElement jsonElement : allCategories) {
            System.out.println(jsonElement.getAsJsonObject().get("name").getAsString());
        }
        System.out.println();
    }

    public static void showByCategory(String json){
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        JsonArray jsonArray = jo.getAsJsonObject("playlists").getAsJsonArray("items");
        for (JsonElement jsonElement : jsonArray) {
            System.out.println(jsonElement.getAsJsonObject().get("name").getAsString());
            System.out.println(jsonElement.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString());
            System.out.println();
        }
    }

    public static String getCategoryId(String json, String category) {
        JsonArray jsonArray = getAllCategories(json);
        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.getAsJsonObject().get("name").getAsString().equals(category)){
                return jsonElement.getAsJsonObject().get("id").getAsString();
            }
        }
        return null;
    }

    private static JsonArray getAllCategories(String json) {
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        JsonArray jsonArray = jo.getAsJsonObject("categories").getAsJsonArray("items");
        return jsonArray;
    }
}
