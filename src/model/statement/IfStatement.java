package model.statement;

import exceptions.InvalidTypeException;
import model.programState.SymbolTable;
import model.type.BooleanType;
import model.type.Type;
import model.value.IValue;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.value.BooleanValue;

public record IfStatement(IExpression condition, IStatement thenStatement,
                          IStatement elseStatement) implements IStatement {
    // recordurile sunt finale; nu putem mosteni din record-uri
    @Override
    public ProgramState execute(ProgramState state) {
        IValue value = condition.evaluate(state.symbolTable(), state.heap());
        if (!(value instanceof BooleanValue(boolean value1))) { //pattern matching instance of
            throw new RuntimeException("If statement not boolean");
        }
        IStatement chosenStatement =
                value1 ? thenStatement : elseStatement;
//        IStatement statement;
//        if (booleanValue.getValue())
//            statement = thenStatement;
//        else
//            statement = elseStatement;
        state.executionStack().push(chosenStatement);
        return null;
    }

    @Override
    public String toString() {
        return " if " + condition +
                " then " + thenStatement +
                " else " + elseStatement ;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(condition.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public SymbolTable typeCheck(SymbolTable typeEnv) {
        Type condType = condition.typeCheck(typeEnv);
        if (condType.equals(new BooleanType())) {
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new InvalidTypeException("The condition of IF has not the type boolean");

        }

    }
}