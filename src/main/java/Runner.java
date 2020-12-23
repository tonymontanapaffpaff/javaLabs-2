import by.gsu.pms.Utils;
import by.gsu.pms.beans.WeatherCard;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        try {
            final String URL =
                    "http://api.openweathermap.org/data/2.5/weather?id=5128638&appid=98c9b61d85e0e48254cd1b20cd8f067e";
            final URL templatePath = Runner.class.getResource("template.html");

            File file = Utils.writeFromUrl(URL);
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file)));
            Gson gson = new Gson();
            WeatherCard card = gson.fromJson(reader, WeatherCard.class);

            final String city = card.getCityName();
            final String code = card.getCode();
            final String icon = card.getIcon();
            final String description = card.getDescription();
            final String fullDescription = card.getFullDescription();
            final String temp = card.getTemp();
            final String pressure = card.getPressure();
            final String humidity = card.getHumidity();
            final String minTemp = card.getMinTemp();
            final String maxTemp = card.getMaxTemp();
            final String windSpeed = card.getWindSpeed();
            final String windDirection = card.getWindDirection();
            final String clouds = card.getClouds();

            File htmlTemplateFile = new File(templatePath.getFile());
            String htmlString = Utils.readFromFile(htmlTemplateFile);

            htmlString = htmlString.replace("$city", city);
            htmlString = htmlString.replace("$code", code);
            htmlString = htmlString.replace("$icon", icon);
            htmlString = htmlString.replace("$description", description);
            htmlString = htmlString.replace("$full_description", fullDescription);
            htmlString = htmlString.replace("$temp_avg", temp);
            htmlString = htmlString.replace("$pressure", pressure);
            htmlString = htmlString.replace("$humidity", humidity);
            htmlString = htmlString.replace("$temp_min", minTemp);
            htmlString = htmlString.replace("$temp_max", maxTemp);
            htmlString = htmlString.replace("$wind_speed", windSpeed);
            htmlString = htmlString.replace("$wind_deg", windDirection);
            htmlString = htmlString.replace("$clouds", clouds);

            File newHtmlFile = new File("out/widget.html");
            Utils.writeFromFile(newHtmlFile, htmlString);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
