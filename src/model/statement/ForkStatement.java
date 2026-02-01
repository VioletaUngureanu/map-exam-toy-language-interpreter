package model.statement;

import model.programState.ExecutionStack;
import model.programState.IExecutionStack;
import model.programState.ProgramState;
import model.programState.SymbolTable;

public class ForkStatement implements  IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IExecutionStack executionStack = new ExecutionStack();
        executionStack.push(statement);

        return new ProgramState(
                executionStack,
                state.symbolTable().deepCopy(),
                state.out(),
                state.fileTable(),
                state.heap()
        );
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + statement + ")";
    }
}
