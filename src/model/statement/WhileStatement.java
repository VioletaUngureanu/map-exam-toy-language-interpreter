package model.statement;

import exceptions.InvalidTypeException;
import model.expression.IExpression;
import model.programState.*;
import model.type.BooleanType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.IValue;

public record WhileStatement(IExpression expression, IStatement statement) implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) {


        IValue conditionValue = expression.evaluate(state.symbolTable(), state.heap());

        if (!(conditionValue instanceof BooleanValue booleanCondition)) {
            throw new InvalidTypeException("While condition is not a boolean");
        }
        if (!booleanCondition.value()) {
            return null;

        }
        else{
            state.executionStack().push(this);
            state.executionStack().push(statement);
            return null;
        }

    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type conditionType = expression.typeCheck(typeEnv);
        if (conditionType.equals(new BooleanType())) {
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new InvalidTypeException("While condition is not a boolean");
        }
    }

    @Override
    public String toString() {
        return "(while (" +
                 expression +
                ") " + statement +
                ')';
    }
}
