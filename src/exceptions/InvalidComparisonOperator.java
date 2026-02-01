package exceptions;

public class InvalidComparisonOperator extends RuntimeException {
    public InvalidComparisonOperator(String message) {
        super(message);
    }
}
