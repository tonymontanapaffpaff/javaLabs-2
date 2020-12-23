package by.gsu.pms.beans;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class WeatherCard {

    @SerializedName("coord")
    private final Map<String, JsonElement> coordinates;

    private final List<JsonElement> weather;
    private final JsonElement base;
    private final Map<String, JsonElement> main;
    private final JsonElement visibility;
    private final Map<String, JsonElement> wind;
    private final Map<String, JsonElement> clouds;
    private final JsonElement dt;

    @SerializedName("sys")
    private final Map<String, JsonElement> sunAndCountry;

    private final JsonElement timezone;
    private final JsonElement id;

    @SerializedName("name")
    private final JsonElement cityName;

    @SerializedName("cod")
    private final JsonElement code;

    private Description description;

    public WeatherCard(Map<String, JsonElement> coordinates, List<JsonElement> weather,
                       JsonElement base, Map<String, JsonElement> main, JsonElement visibility,
                       Map<String, JsonElement> wind, Map<String, JsonElement> clouds, JsonElement dt,
                       Map<String, JsonElement> sunAndCountry, JsonElement timezone, JsonElement id, JsonElement cityName,
                       JsonElement code) {
        this.coordinates = coordinates;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sunAndCountry = sunAndCountry;
        this.timezone = timezone;
        this.id = id;
        this.cityName = cityName;
        this.code = code;
    }

    private String tempCalc(double k) {
        return String.valueOf((int) (k - 273.5));
    }

    public String getClouds() {
        return clouds.get("all").toString();
    }

    public String getCityName() {
        return cityName.toString();
    }

    public String getCode() {
        return sunAndCountry.get("country").toString();
    }

    public String getTemp() {
        return tempCalc(main.get("temp").getAsDouble());
    }

    public String getPressure() {
        return main.get("pressure").toString();
    }

    public String getHumidity() {
        return main.get("humidity").toString();
    }

    public String getMinTemp() {
        return tempCalc(main.get("temp_min").getAsDouble());
    }

    public String getMaxTemp() {
        return tempCalc(main.get("temp_max").getAsDouble());
    }

    public String getWindSpeed() {
        return wind.get("speed").toString();
    }

    public String getWindDirection() {
        return wind.get("deg").toString();
    }

    public String getIcon() {
        Gson gson = new Gson();
        description = gson.fromJson(weather.get(0).toString(), Description.class);
        return description.getIcon();
    }

    public String getDescription() {
        return description.getMain();
    }

    public String getFullDescription() {
        return description.getWeatherInformation();
    }
}
