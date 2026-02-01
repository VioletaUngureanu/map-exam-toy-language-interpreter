package model.statement;

import model.programState.ProgramState;
import model.programState.ISymbolTable;
import model.programState.SymbolTable;
import model.type.Type;

public record VariableDeclarationStatement(Type type, String variableName) implements IStatement {

    @Override
    public String toString() {
        return " " + type +
                " " + variableName ;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        // logica poate fi pusa in symbolTable
        ISymbolTable symbolTable = state.symbolTable();
        if (symbolTable.isDefined(variableName)) {
            throw new RuntimeException("Variable " + variableName + "is already defined");
        } else
            symbolTable.declareVariable(variableName, type);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(type, variableName);
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        typeEnv.declareVariable(variableName, type);
        return typeEnv;
    }

}
