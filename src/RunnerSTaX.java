import by.gsu.pms.Constants;
import by.gsu.pms.Currency;
import by.gsu.pms.CurrencyReader;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RunnerSTaX {
    public static void main(String[] args) {
        List<Currency> currencyList = new ArrayList<>();

        int id;
        int numCode = 0;
        String charCode = null;
        int scale = 0;
        String name = null;
        double rate = 0.0;

        try (CurrencyReader reader = new CurrencyReader(new URL(Constants.URL).openStream())) {
            while (reader.startElement("Currency", "DailyExRates")) {
                id = Integer.parseInt(reader.getAttribute("Id"));

                if (reader.startElement("NumCode", "Currency")) {
                    numCode = Integer.parseInt(reader.getText());
                }
                if (reader.startElement("CharCode", "Currency")) {
                    charCode = reader.getText();
                }
                if (reader.startElement("Scale", "Currency")) {
                    scale = Integer.parseInt(reader.getText());
                }
                if (reader.startElement("Name", "Currency")) {
                    name = reader.getText();
                }
                if (reader.startElement("Rate", "Currency")) {
                    rate = Double.parseDouble(reader.getText());
                }

                currencyList.add(new Currency(id, numCode, charCode, scale, name, rate));
            }

            currencyList.forEach(System.out::println);
        } catch (XMLStreamException | IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
