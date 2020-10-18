package by.gsu.pms;

public class ParseDataException extends IllegalArgumentException {

    public ParseDataException() {
    }

    public ParseDataException(String s) {
        super(s);
    }

    public ParseDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseDataException(Throwable cause) {
        super(cause);
    }
}
