package model.expression;

import exceptions.InvalidComparisonOperator;
import exceptions.InvalidExpression;
import exceptions.InvalidTypeException;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.IntType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.IntegerValue;

public record RelationalExpression(IExpression expression1,
                                   String operator, IExpression expression2) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        IValue value1, value2;
        value1 = expression1.evaluate(symbolTable, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(symbolTable, heap);
            if (value2.getType().equals(new IntType())) {
                int number1 = ((IntegerValue) value1).value();
                int number2 = ((IntegerValue) value2).value();
                return switch (operator) {
                    case "<" -> new BooleanValue(number1 < number2);
                    case "<=" -> new BooleanValue(number1 <= number2);
                    case "==" -> new BooleanValue(number1 == number2);
                    case "!=" -> new BooleanValue(number1 != number2);
                    case ">" -> new BooleanValue(number1 > number2);
                    case ">=" -> new BooleanValue(number1 >= number2);
                    default -> throw new InvalidComparisonOperator("Unexpected value: " + operator);
                };
            } else {
                throw new InvalidExpression("Second operand is not an integer");
            }
        } else {
            throw new InvalidExpression("First operand is not an integer");
            }
        }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(expression1.deepCopy(), operator, expression2.deepCopy());
    }

    @Override
    public Type typeCheck(ISymbolTable typeEnv) {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new model.type.BooleanType();
            } else {
                throw new InvalidTypeException("Second operand is not an integer");
            }
        } else {
            throw new InvalidTypeException("First operand is not an integer");
        }
    }

    @Override
    public String toString() {
        return
                 expression1 + " " +
                 operator +
                " " + expression2 ;
    }
}


