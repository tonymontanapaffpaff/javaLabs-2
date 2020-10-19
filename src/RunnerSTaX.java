import by.gsu.pms.exceptions.CloseException;
import by.gsu.pms.Constants;
import by.gsu.pms.beans.Currency;
import by.gsu.pms.handlers.CurrencyReader;

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
            while (reader.startElement(Constants.CURRENCY_ELEM, Constants.DAILY_RATES_ELEM)) {
                id = Integer.parseInt(reader.getAttribute(Constants.ID_ELEM));

                if (reader.startElement(Constants.NUM_CODE_ELEM, Constants.CURRENCY_ELEM)) {
                    numCode = Integer.parseInt(reader.getText());
                }
                if (reader.startElement(Constants.CHAR_CODE_ELEM, Constants.EMPTY_STRING)) {
                    charCode = reader.getText();
                }
                if (reader.startElement(Constants.SCALE_ELEM, Constants.EMPTY_STRING)) {
                    scale = Integer.parseInt(reader.getText());
                }
                if (reader.startElement(Constants.NAME_ELEM, Constants.EMPTY_STRING)) {
                    name = reader.getText();
                }
                if (reader.startElement(Constants.RATE_ELEM, Constants.EMPTY_STRING)) {
                    rate = Double.parseDouble(reader.getText());
                }

                currencyList.add(new Currency(id, numCode, charCode, scale, name, rate));
            }

            currencyList.forEach(System.out::println);
        } catch (XMLStreamException | IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (CloseException e) {
            System.err.println(e.getMessage());
        }
    }
}
