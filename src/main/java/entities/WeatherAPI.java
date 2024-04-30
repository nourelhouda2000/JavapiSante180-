package entities;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class WeatherAPI {
    private static String API_KEY = "170f0715192447a7a1f92254243004";
    private static String API_URL = "http://api.meteo.com/weather?q=%s&apikey=%s";

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }

    public static JSONObject getWeather(String city) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(API_URL, city, API_KEY)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return new JSONObject(response.body());
        } else {
            throw new Exception("Failed to fetch weather data");
        }
    }
}
