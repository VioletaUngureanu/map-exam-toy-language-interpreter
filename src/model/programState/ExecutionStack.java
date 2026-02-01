package model.programState;

import model.ADT.stack.IStack;
import model.ADT.stack.Stack;
import model.statement.IStatement;

public class ExecutionStack implements IExecutionStack{
    private final IStack<IStatement> stack = new Stack<>();

    @Override
    public void push(IStatement chosenStatement) {
        this.stack.push(chosenStatement);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public IStatement pop() {
        return stack.pop();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
