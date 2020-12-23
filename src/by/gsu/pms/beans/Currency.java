package by.gsu.pms.beans;

import by.gsu.pms.Constants;
import by.gsu.pms.exceptions.ParseDataException;

public class Currency {
    private int id;
    private int numCode;
    private String charCode;
    private int scale;
    private String name;
    private double rate;

    public Currency() {
    }

    public Currency(int id, int numCode, String charCode, int scale, String name, double rate) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.scale = scale;
        this.name = name;
        this.rate = rate;
    }

    public Currency(String id, String numCode, String charCode, String scale, String name, String rate) {
        try {
            this.id = Integer.parseInt(id);
            this.numCode = Integer.parseInt(numCode);
            this.charCode = charCode;
            this.scale = Integer.parseInt(scale);
            this.name = name;
            this.rate = Double.parseDouble(rate);
        } catch (NumberFormatException e) {
            throw new ParseDataException(e);
        }
    }

    @Override
    public String toString() {
        return id + Constants.DELIMITER + numCode + Constants.DELIMITER
                + charCode + Constants.DELIMITER + scale + Constants.DELIMITER +
                name + Constants.DELIMITER + rate;
    }
}
