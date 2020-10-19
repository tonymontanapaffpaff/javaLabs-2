package by.gsu.pms.handlers;

import by.gsu.pms.beans.Currency;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CurrencyHandler extends DefaultHandler {

    private enum CurrencyEnum {
        DAILYEXRATES, CURRENCY, ID, NUMCODE, CHARCODE, SCALE, NAME, RATE;
    }

    private List<Currency> currencyList = new ArrayList<>();
    private CurrencyEnum currencyEnum;
    private String id;
    private String numCode;
    private String charCode;
    private String scale;
    private String name;
    private String rate;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currencyEnum = CurrencyEnum.valueOf(localName.toUpperCase());
        if (currencyEnum == CurrencyEnum.CURRENCY) {
            final int ID_INDEX = 0;
            id = attributes.getValue(ID_INDEX);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (currencyEnum == CurrencyEnum.NUMCODE) {
            if (!str.isEmpty()) {
                numCode = str;
            }
        }
        if (currencyEnum == CurrencyEnum.CHARCODE) {
            if (!str.isEmpty()) {
                charCode = str;
            }
        }
        if (currencyEnum == CurrencyEnum.SCALE) {
            if (!str.isEmpty()) {
                scale = str;
            }
        }
        if (currencyEnum == CurrencyEnum.NAME) {
            if (!str.isEmpty()) {
                name = str;
            }
        }
        if (currencyEnum == CurrencyEnum.RATE) {
            if (!str.isEmpty()) {
                rate = str;
                Currency result = new Currency(id, numCode, charCode, scale, name, rate);
                currencyList.add(result);
            }
        }
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }
}
