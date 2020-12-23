import by.gsu.pms.Constants;
import by.gsu.pms.beans.Currency;
import by.gsu.pms.handlers.CurrencyHandler;
import by.gsu.pms.exceptions.ParseDataException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class RunnerSAX {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        CurrencyHandler handler = new CurrencyHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL(Constants.URL).openStream()), handler);
            for (Currency currency : handler.getCurrencyList()) {
                System.out.println(currency);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
        } catch (ParseDataException e) {
            System.err.println(e.getMessage());
        }
    }
}
