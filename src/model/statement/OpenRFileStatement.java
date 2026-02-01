package model.statement;

import exceptions.FileAlreadyOpenedException;
import exceptions.FileOperationException;
import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.IFileTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public record OpenRFileStatement(IExpression expression) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {
        var fileName = FileStatementUtil.evaluateToString(expression, state.symbolTable(), state.heap());

        if (state.fileTable().isOpened(fileName))
            throw new FileAlreadyOpenedException();

        try {
            var bufferedReader = new BufferedReader(new FileReader(fileName));
            state.fileTable().add(new StringValue(fileName), bufferedReader);
        } catch (IOException ioException) {
            throw new FileOperationException(ioException.getMessage());
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFileStatement(expression.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        var exprType = expression.typeCheck(typeEnv);
        if (exprType.equals(new model.type.StringType()))
            return typeEnv;
        else
            throw new InvalidTypeException("OpenRFileStatement: expression is not of StringType");
    }

    @Override
    public String toString() {
        return
                "openRFile(" + expression +
                ')';
    }
}
