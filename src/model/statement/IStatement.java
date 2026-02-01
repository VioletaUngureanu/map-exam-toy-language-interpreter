package model.statement;

import model.programState.ProgramState;
import model.programState.SymbolTable;

public interface IStatement {
    ProgramState execute(ProgramState state);
    IStatement deepCopy();
    SymbolTable typeCheck(SymbolTable typeEnv);
}
