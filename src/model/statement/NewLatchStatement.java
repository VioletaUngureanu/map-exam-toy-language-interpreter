package model.statement;

import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.IntType;
import model.type.Type;
import model.value.IValue;
import model.value.IntegerValue;

public record NewLatchStatement(String variableName, IExpression expression) implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) {
        ISymbolTable symbolTable = state.symbolTable();
        IHeap heap = state.heap();
        IValue resultExpression = expression.evaluate(symbolTable, heap);
        if (!(resultExpression.getType().equals(new IntType())))
            throw new InvalidTypeException("The expression should result in an integer");
        else
        {
            synchronized (state.latchTable()) {
                int newLockAddress = state.latchTable().getNewFreeLocation();
                state.latchTable().put(newLockAddress,  ((IntegerValue)resultExpression).value());
                symbolTable.updateValue(variableName, new IntegerValue(newLockAddress));
            }
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewLatchStatement(variableName, expression.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type typeVariable = typeEnv.getVariableType(variableName);
        Type expressionType = expression.typeCheck(typeEnv);
        if (!(typeVariable.equals(new IntType())))
            throw new InvalidTypeException("The variable " + variableName+ " should be an integer");
        if (!(expressionType.equals(new IntType())))
            throw new InvalidTypeException("The expression "+expression + " should evaluate to an integer");
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newLatch("  + variableName +
                ", " + expression +
                ')';
    }
}
