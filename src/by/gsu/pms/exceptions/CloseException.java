package by.gsu.pms.exceptions;

public class CloseException extends Exception {

    public CloseException() {
    }

    public CloseException(String message) {
        super(message);
    }

    public CloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloseException(Throwable cause) {
        super(cause);
    }
}
