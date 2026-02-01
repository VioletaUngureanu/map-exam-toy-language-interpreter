package model.statement;

import exceptions.MemoryViolationException;
import exceptions.VariableNotDefinedException;
import model.expression.IExpression;
import model.programState.ISymbolTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.ReferenceType;

public record HeapWritingStatement(String variableName, IExpression expression) implements IStatement {


    @Override
    public ProgramState execute(ProgramState state) {

        ISymbolTable symbolTable = state.symbolTable();
        if (!(symbolTable.isDefined(variableName)))
            throw new VariableNotDefinedException("Heap writing error: Variable " + variableName + " is not defined");

        if (!(symbolTable.getVariableType(variableName) instanceof ReferenceType refType)){
            throw new VariableNotDefinedException("Heap writing error: Variable " + variableName + " is not of Reference Type");}

        int address = ((model.value.ReferenceValue) symbolTable.lookup(variableName)).heapAddress();
        model.programState.IHeap heap = state.heap();
        if (!heap.isDefined(address)){
            throw new MemoryViolationException("Heap writing error: Address " + address + " is not defined in the heap");}

        model.value.IValue value = expression.evaluate(symbolTable, heap);
        if (!value.getType().equals(refType.innerType())){
            throw new VariableNotDefinedException("Heap writing error: Type of variable " + variableName + " and type of expression do not match");}

        heap.update(address, value);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapWritingStatement(variableName, expression.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        if (!(typeEnv.lookup(variableName).getType() instanceof ReferenceType refType)){
            throw new VariableNotDefinedException("Heap writing error: Variable " + variableName + " is not of Reference Type");}

        model.type.Type expType = expression.typeCheck(typeEnv);
        if (!expType.equals(refType.innerType())){
            throw new VariableNotDefinedException("Heap writing error: Type of variable " + variableName + " and type of expression do not match");}

        return typeEnv;
    }

    @Override
    public String toString() {
        return "wH(" +
                 variableName + ',' +
                 expression +
                ')';
    }
}
