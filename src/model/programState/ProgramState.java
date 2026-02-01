package model.programState;

import exceptions.ExecutionStackEmpty;
import model.statement.IStatement;

public class ProgramState {
    private final IExecutionStack executionStack;
    private final SymbolTable symbolTable;
    private final IOut out;
    private final IFileTable fileTable;
    private final IHeap heap;
    private static int nextId = 0;
    private final int id;

    public ProgramState(IExecutionStack executionStack, SymbolTable symbolTable, IOut out, IFileTable fileTable, IHeap heap) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;

        this.id = getNextId();
    }

    public int id() {
        return id;
    }

    private synchronized static int getNextId() {
        return ++nextId;
    }
    public IHeap heap() {
        return heap;
    }

    public IFileTable fileTable() {
        return fileTable;
    }

    public IOut out() {
        return out;
    }

    public SymbolTable symbolTable() {
        return symbolTable;
    }

    public IExecutionStack executionStack() {
        return executionStack;
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n" +
                "ExecutionStack:\n" + executionStack +
                "SymbolTable:\n" + symbolTable +
                "Out:\n" + out +
                "FileTable:\n" + fileTable +
                "Heap:\n" + heap;
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws ExecutionStackEmpty {
        if (executionStack.isEmpty()) {
            throw new ExecutionStackEmpty("Program state stack is empty!");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }
}