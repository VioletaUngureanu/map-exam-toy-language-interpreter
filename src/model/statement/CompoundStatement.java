package model.statement;

import model.programState.IExecutionStack;
import model.programState.ProgramState;
import model.programState.SymbolTable;

public record CompoundStatement(IStatement first, IStatement second) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {
        IExecutionStack executionStack = state.executionStack();
        executionStack.push(second);
        executionStack.push(first);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return " " + first +
                ";" + second ;
    }
}
