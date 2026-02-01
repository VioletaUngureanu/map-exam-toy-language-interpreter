package model.statement;

import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.value.StringValue;

public class FileStatementUtil {
    public static String evaluateToString(IExpression expression, ISymbolTable symbolTable, IHeap heap) throws InvalidTypeException {
        if (!(expression.evaluate(symbolTable,heap)
                instanceof StringValue(String stringValue)))
            throw new InvalidTypeException("File operation error: expression does not evaluate to a string value");
        return stringValue;
    }
}
