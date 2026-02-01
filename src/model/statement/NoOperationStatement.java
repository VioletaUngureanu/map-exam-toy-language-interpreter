package model.statement;

import model.programState.ProgramState;
import model.programState.SymbolTable;

public class NoOperationStatement implements IStatement{

    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        return typeEnv;
    }
}
