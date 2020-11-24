package by.gsu.pms.beans;

import com.google.gson.annotations.SerializedName;

public class Description {
    private final String id;
    private final String main;

    @SerializedName("description")
    private final String weatherInformation;

    private final String icon;

    public Description(String id, String main, String weatherInformation, String icon) {
        this.id = id;
        this.main = main;
        this.weatherInformation = weatherInformation;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getWeatherInformation() {
        return weatherInformation;
    }

    public String getIcon() {
        return icon;
    }
}
