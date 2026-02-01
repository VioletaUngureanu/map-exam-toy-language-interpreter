package exceptions;

public class ReadingFromEmptyCollection extends RuntimeException {
    public ReadingFromEmptyCollection(String message) {
        super(message);
    }
}
