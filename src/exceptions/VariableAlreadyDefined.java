package exceptions;

public class VariableAlreadyDefined extends RuntimeException {
    public VariableAlreadyDefined(String message) {
        super(message);
    }
}
