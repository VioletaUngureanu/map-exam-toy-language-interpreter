package model.statement;

import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.expression.IExpression;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.ReferenceType;
import model.type.Type;
import model.value.IValue;
import model.value.ReferenceValue;

public record HeapAllocationStatement(
        String variableName,
        IExpression expression) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {

        ISymbolTable symbolTable = state.symbolTable();

        if (!(symbolTable.isDefined(variableName)))
            throw new VariableNotDefinedException("Heap allocation error: Variable " + variableName + " is not defined");

        Type variableType = symbolTable.getVariableType(variableName);

        if (!(variableType instanceof ReferenceType))
            throw new InvalidTypeException("Heap allocation error: Variable " + variableName + " is not of Reference Type");

        IHeap heap = state.heap();
        IValue value = expression.evaluate(symbolTable, heap);
        Type locationValueType = ((ReferenceType)variableType).innerType();
        if (!value.getType().equals(locationValueType))
            throw new InvalidTypeException("Heap allocation error: "+ variableName +" is not of type " + value.getType());

//        int location = heap.add(value);
//        symbolTable.updateValue(variableName, new ReferenceValue(location, variableType));
        int location = heap.add(value);
        symbolTable.updateValue(variableName, new ReferenceValue(location, value.getType()));

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapAllocationStatement(variableName, expression.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type varType = typeEnv.lookup(variableName).getType();
        Type expType = expression.typeCheck(typeEnv);
        if (varType.equals(new ReferenceType(expType)))
            return typeEnv;
        else
            throw new InvalidTypeException("Heap allocation error: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new(" +
                 variableName + ',' +
                 expression +
                ')';
    }
}
