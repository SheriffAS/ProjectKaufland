package sk.itsovy.ProjectKaufland.Main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {
    //getUsd
    public static Double getUSD() throws IOException {
            URL urlForGetRequest = new URL("http://data.fixer.io/api/latest?access_key=384b4b5ac67073bfe258ec315a1bdb4f");
             double dollars;

        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        jsonobj = jsonobj.getAsJsonObject("rates");
        dollars = jsonobj.get("USD").getAsDouble();

        return dollars;
    }

}

//url: http://data.fixer.io/api/latest?access_key=384b4b5ac67073bfe258ec315a1bdb4f