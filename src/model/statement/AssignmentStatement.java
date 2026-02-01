package model.statement;


import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.programState.ISymbolTable;
import model.programState.SymbolTable;
import model.value.IValue;
import model.type.Type;

public record AssignmentStatement(String variableName, IExpression expression) implements IStatement {


    @Override
    public ProgramState execute(ProgramState state) {
        ISymbolTable symbolTable = state.symbolTable();
        if (!(symbolTable.isDefined(variableName)))
            throw new RuntimeException("Variable " + variableName + "is not defined");

        IValue value = expression.evaluate(symbolTable, state.heap());
        if (!(value.getType().equals(symbolTable.getVariableType(variableName))) ){
            throw new RuntimeException();
        }
        symbolTable.updateValue(variableName, value);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(variableName, expression.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type varType = typeEnv.lookup(variableName).getType();
        Type expType = expression.typeCheck(typeEnv);
        if (varType.equals(expType))
            return typeEnv;
        else
            throw new InvalidTypeException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return " " + variableName +
                "=" + expression ;
    }
}
