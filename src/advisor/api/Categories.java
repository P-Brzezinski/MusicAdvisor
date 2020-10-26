package advisor.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Categories {

    public static void show(String json){
        JsonObject jo = JsonParser.parseString(json).getAsJsonObject();

        JsonArray asJsonArray = jo.getAsJsonObject("categories").getAsJsonArray("items");

        for (JsonElement jsonElement : asJsonArray) {
            System.out.println(jsonElement.getAsJsonObject().get("name").getAsString());
        }
        System.out.println();
    }
}
