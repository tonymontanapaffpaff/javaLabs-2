import by.gsu.pms.Constants;
import by.gsu.pms.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RunnerDOM {

    private static String getElem(Element elem, String elemName) {
        final int ELEM_IND = 0;
        return elem.getElementsByTagName(elemName)
                .item(ELEM_IND).getTextContent();
    }

    public static void main(String[] args) {
        List<Currency> currencyList = new ArrayList<>();

        int id;
        int numCode;
        String charCode;
        int scale;
        String name;
        double rate;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL(Constants.URL).openStream());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(Constants.CURRENCY_ELEM);

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    id = Integer.parseInt(eElement.getAttribute(Constants.ID_ELEM));
                    numCode = Integer.parseInt(getElem(eElement, Constants.NUMCODE_ELEM));
                    charCode = (getElem(eElement, Constants.CHARCODE));
                    scale = Integer.parseInt((getElem(eElement, Constants.SCALE_ELEM)));
                    name = (getElem(eElement, Constants.NAME_ELEM));
                    rate = Double.parseDouble((getElem(eElement, Constants.RATE_ELEM)));
                    currencyList.add(new Currency(id, numCode, charCode, scale, name, rate));
                }
            }

            currencyList.forEach(System.out::println);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
