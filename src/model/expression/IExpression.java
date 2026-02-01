package model.expression;


import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.Type;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable, IHeap heap);
    IExpression deepCopy();
    Type typeCheck(ISymbolTable typeEnv);
}