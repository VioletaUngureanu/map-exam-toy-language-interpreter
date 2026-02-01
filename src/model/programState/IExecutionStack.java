package model.programState;

import model.statement.IStatement;

public interface IExecutionStack {
    void push(IStatement chosenStatement);

    boolean isEmpty();

    IStatement pop();
}
