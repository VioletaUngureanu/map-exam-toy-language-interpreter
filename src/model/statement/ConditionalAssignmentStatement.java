package model.statement;

import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.programState.SymbolTable;
import model.type.BooleanType;
import model.type.Type;


public record ConditionalAssignmentStatement(String variableName, IExpression condition, IExpression trueExpression, IExpression falseExpression) implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {
        state.executionStack().push(
                new IfStatement(
                        condition,
                        new AssignmentStatement(variableName, trueExpression),
                        new AssignmentStatement(variableName, falseExpression)
                )
        );
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ConditionalAssignmentStatement(
                variableName,
                condition.deepCopy(),
                trueExpression.deepCopy(),
                falseExpression.deepCopy()
        );
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type conditionType = condition.typeCheck(typeEnv);
        if (!conditionType.equals(new BooleanType())) {
            throw new InvalidTypeException("ConditionalAssignmentStatement: condition is not of BooleanType");
        }
        Type trueType = trueExpression.typeCheck(typeEnv);
        Type falseType = falseExpression.typeCheck(typeEnv);
        Type variableType = typeEnv.getVariableType(variableName);
        if (!trueType.equals(variableType)) {
            throw new InvalidTypeException("ConditionalAssignmentStatement: true expression type does not match variable type");
        }
        if (!falseType.equals(variableType)) {
            throw new InvalidTypeException("ConditionalAssignmentStatement: false expression type does not match variable type");
        }
        return typeEnv;
    }

    @Override
    public String toString() {
        return " " + variableName + "=(" + condition +
                ")?" + trueExpression +
                ":" + falseExpression;
    }
}
