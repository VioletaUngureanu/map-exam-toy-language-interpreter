package model.statement;


import exceptions.InvalidTypeException;
import exceptions.LatchNotDefinedException;
import model.programState.ILatchTable;
import model.programState.ISymbolTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.IntType;
import model.type.Type;
import model.value.IValue;
import model.value.IntegerValue;

public record AwaitStatement(String variableName) implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) {
        ISymbolTable symbolTable = state.symbolTable();
        ILatchTable latchTable = state.latchTable();
        IValue foundIndex = symbolTable.lookup(variableName);
        if (!(foundIndex.getType().equals(new IntType())))
            throw  new InvalidTypeException("The variable is not an integer");
        if (!latchTable.containsKey(((IntegerValue) foundIndex).value()))
        {
            throw new LatchNotDefinedException("The latch does not exist");
        }
        else
        {
            int latchValue = latchTable.lookup(((IntegerValue) foundIndex).value());
            if(!(latchValue == 0))
                state.executionStack().push(this);

        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AwaitStatement(variableName);
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type typeVariable = typeEnv.getVariableType(variableName);

        if (!(typeVariable.equals(new IntType())))
            throw new InvalidTypeException("The variable "+ variableName+ " should be an integer");

        return typeEnv;
    }

    @Override
    public String toString() {
        return "await( " + variableName +  ')';
    }
}
