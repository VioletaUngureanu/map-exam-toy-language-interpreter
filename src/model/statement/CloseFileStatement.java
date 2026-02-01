package model.statement;

import exceptions.FileOperationException;
import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.IFileTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.StringType;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record CloseFileStatement(IExpression expression) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {
        var fileName = FileStatementUtil.evaluateToString(expression, state.symbolTable(), state.heap());
        try {
            BufferedReader file = state.fileTable().getFile(fileName);
            file.close();
        } catch (IOException e) {
            throw new FileOperationException(e.getMessage());
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {

        var type = expression.typeCheck(typeEnv);
        if (!type.equals(new StringType()))
            throw new InvalidTypeException("CloseFileStatement: expression must be of StringType");
        return typeEnv;
    }

    @Override
    public String toString() {
        return "closeRFile(" + expression +
                ')';
    }
}
