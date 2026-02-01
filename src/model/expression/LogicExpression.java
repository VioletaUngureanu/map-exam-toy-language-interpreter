package model.expression;

import exceptions.InvalidTypeException;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.BooleanType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.IValue;
import exceptions.InvalidExpression;

public record LogicExpression(IExpression expression1, IExpression expression2,
                              String operator) implements IExpression {
    /**
     * @throws InvalidExpression
     */
    public LogicExpression {
        if (!operator.equals("&&") && !operator.equals("||"))
            throw new InvalidExpression("Invalid logic operator");
    }


    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        IValue value1;
        IValue value2;
        value1 = expression1.evaluate(symbolTable, heap);
        if (value1.getType().equals(new BooleanType())) {
            value2 = expression2.evaluate(symbolTable, heap);
            if (value2.getType().equals(new BooleanType())) {
                BooleanValue bool1 = (BooleanValue) value1;
                BooleanValue bool2 = (BooleanValue) value2;
                boolean booleanValue1, booleanValue2;
                booleanValue1 = bool1.value();
                booleanValue2 = bool2.value();
                if (operator.equals("&&"))
                    return new BooleanValue(booleanValue1 && booleanValue2);
                if (operator.equals("||"))
                    return new BooleanValue(booleanValue1 || booleanValue2);
            } else throw new InvalidExpression("Second operand is not a boolean");
        } else throw new InvalidExpression("First operand is not a boolean");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(expression1.deepCopy(), expression2.deepCopy(), operator);
    }

    @Override
    public Type typeCheck(ISymbolTable typeEnv) {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new BooleanType())) {
            if (type2.equals(new BooleanType())) {
                return new BooleanType();
            } else {
                throw new InvalidTypeException("Second operand is not a boolean");

            }
        }
        else {
            throw new InvalidTypeException("First operand is not a boolean");
        }
    }

    @Override
    public String toString() {
        return expression1 + " " + operator +
                " " + expression2;
    }
}
