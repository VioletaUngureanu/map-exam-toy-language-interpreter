package model.expression;

import exceptions.MemoryViolationException;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.ReferenceType;
import model.type.Type;
import model.value.IValue;

public record HeapReadingExpression(IExpression expression) implements IExpression {
    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {


        IValue value = expression.evaluate(symbolTable, heap);

        if (!(value.getType() instanceof ReferenceType))
            throw new exceptions.InvalidTypeException("Heap reading error: Expression " + expression.toString() + " is not of Reference Type");

        int address = ((model.value.ReferenceValue) value).heapAddress();
        if (!heap.isDefined(address))
            throw new MemoryViolationException("Heap reading error: Address " + address + " is not defined in the heap");
        return heap.get(address);
    }

    @Override
    public IExpression deepCopy() {
        return new HeapReadingExpression(expression.deepCopy());
    }

    @Override
    public Type typeCheck(ISymbolTable typeEnv) {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof ReferenceType(Type innerType)) {
            return innerType;
        }
        else
            throw new exceptions.InvalidTypeException("Heap reading error: Expression " + expression.toString() + " is not of Reference Type");

    }

    @Override
    public String toString() {
        return "rH(" +
                 expression +
                ')';
    }
}
