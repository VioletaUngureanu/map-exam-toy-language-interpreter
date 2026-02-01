package model.expression;

import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.Type;
import model.value.IValue;

public record ValueExpression(IValue value) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        return value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value.deepCopy());
    }

    @Override
    public Type typeCheck(ISymbolTable typeEnv) {
        return value.getType();
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
