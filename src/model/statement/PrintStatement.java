package model.statement;

import model.programState.SymbolTable;
import model.value.IValue;
import model.expression.IExpression;
import model.programState.ProgramState;

public record PrintStatement(IExpression expression) implements IStatement {

    @Override
    public String toString() {
        return " print(" + expression + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IValue value = expression.evaluate((state.symbolTable()), state.heap());
        state.out().append(value);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
