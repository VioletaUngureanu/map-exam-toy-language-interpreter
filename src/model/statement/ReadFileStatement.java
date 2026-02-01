package model.statement;

import exceptions.*;
import model.expression.IExpression;
import model.programState.ISymbolTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.IntType;
import model.value.IntegerValue;

import java.io.IOException;

public record ReadFileStatement(IExpression expression, String varName) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {
        ISymbolTable symbolTable = state.symbolTable();
        if (!symbolTable.isDefined(varName)) {
            throw new VariableNotDefinedException("Variable " + varName + " is not defined.");
        }
        if (!symbolTable.getVariableType(varName).equals(new IntType())) {
            throw new InvalidTypeException("Variable " + varName + " is not of type int.");
        }

        var fileName = FileStatementUtil.evaluateToString(expression, symbolTable, state.heap());

        if (!state.fileTable().isOpened(fileName)) {
            throw new FileNotOpenException();
        }

        var file = state.fileTable().getFile(fileName);

        try {
            String line = file.readLine();
            int val = Integer.parseInt(line);
            IntegerValue value = new IntegerValue(val);
            state.symbolTable().updateValue(varName, value);

        } catch (IOException e) {
            throw new FileOperationException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NotAnIntegerException(e.getMessage());
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), varName);
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        var exprType = expression.typeCheck(typeEnv);
        if (!exprType.equals(new model.type.StringType())) {
            throw new InvalidTypeException("ReadFile: expression " + expression.toString() + " is not of type string.");
        }
        if (!typeEnv.isDefined(varName)) {
            throw new VariableNotDefinedException("Variable " + varName + " is not defined.");
        }
        if (!typeEnv.lookup(varName).getType().equals(new IntType())) {
            throw new InvalidTypeException("ReadFile: variable " + varName + " is not of type int.");
        }
        return typeEnv;
    }

    @Override
    public String toString() {
        return "readFile(" + expression +
                ", " + varName  +
                ')';
    }
}
