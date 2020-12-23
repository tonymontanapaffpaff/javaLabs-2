package by.gsu.pms;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {

    private Utils() {
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder jsonText = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            int cp;
            while ((cp = rd.read()) != -1) {
                jsonText.append((char) cp);
            }
        }
        return jsonText.toString();
    }

    public static String readFromFile(File file) throws IOException {
        StringBuilder htmlText = new StringBuilder();
        try (Scanner sc = new Scanner(new FileReader(file))) {
            while (sc.hasNext()) {
                htmlText.append(sc.nextLine());
            }
        }
        return htmlText.toString();
    }

    public static File writeFromUrl(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        String json = readFromStream(inputStream);
        File out = new File("out/weather.json");
        writeFromFile(out, json);
        return out;
    }

    public static void writeFromFile(File file, String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(text);
        }
    }
}
