import by.gsu.pms.Currency;
import by.gsu.pms.CurrencyHandler;
import by.gsu.pms.ParseDataException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class Runner {
    public static void main(String[] args) {
        final String url = "http://www.nbrb.by/Services/XmlExRates.aspx";
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        CurrencyHandler handler = new CurrencyHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL(url).openStream()), handler);
            for (Currency currency : handler.getCurrencyList()) {
                System.out.println(currency);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } catch (ParseDataException e) {
            e.printStackTrace();
        }
    }
}
