package exceptions;

public class InvalidProgram extends RuntimeException {
    public InvalidProgram(String message) {
        super(message);
    }
}
