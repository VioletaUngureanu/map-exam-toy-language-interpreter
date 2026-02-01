package exceptions;

public class MemoryViolationException extends RuntimeException {
    public MemoryViolationException(String message) {
        super(message);
    }
}
