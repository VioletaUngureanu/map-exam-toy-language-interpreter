package model.expression;

import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.Type;
import model.value.IValue;

public record VariableExpression(String name) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symTable, IHeap heap) {
        return symTable.lookup(name);
    }

    @Override
    public String toString() {
        return "" + name;
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(name);
    }

    @Override
    public Type typeCheck(ISymbolTable typeEnv) {
        return typeEnv.lookup(name).getType();
    }
}
