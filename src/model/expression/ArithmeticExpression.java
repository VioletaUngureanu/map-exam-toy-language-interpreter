package model.expression;

import exceptions.InvalidTypeException;
import model.programState.IHeap;
import model.programState.ISymbolTable;
import model.type.Type;
import model.value.IValue;
import model.value.IntegerValue;
import exceptions.DivisionByZero;
import exceptions.InvalidExpression;
import model.type.IntType;

public record ArithmeticExpression(IExpression expression1, IExpression expression2,
                                   char operator) implements IExpression {
    private static final int IMPOSSIBLE_DIVISION = 0;


    public ArithmeticExpression {
        if (operator != '+' && operator != '-' && operator != '*' && operator != '/')
            throw new InvalidExpression("Invalid arithmetic operator");
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) throws DivisionByZero, InvalidExpression {
        IValue value1;
        IValue value2;
        value1 = expression1.evaluate(symbolTable, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(symbolTable, heap);
            if (value2.getType().equals(new IntType())) {
                IntegerValue int1 = (IntegerValue) value1;
                IntegerValue int2 = (IntegerValue) value2;
                int number1, number2;
                number1 = int1.value();
                number2 = int2.value();
                if (operator == '+')
                    return new IntegerValue(number1 + number2);
                if (operator == '-')
                    return new IntegerValue(number1 - number2);
                if (operator == '*')
                    return new IntegerValue(number1 * number2);
                if (operator == '/') {
                    if (number2 == IMPOSSIBLE_DIVISION)
                        throw new DivisionByZero("Division by zero");
                    return new IntegerValue(number1 / number2);
                }
            } else throw new InvalidExpression("Second operand is not an integer");
        } else throw new InvalidExpression("First operand is not an integer");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(expression1.deepCopy(), expression2.deepCopy(), operator);
    }

    @Override
    public Type typeCheck(ISymbolTable typeEnv) {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else {
                throw new InvalidTypeException("Second operand is not an integer");

            }  }
            else {
            throw new InvalidTypeException("First operand is not an integer");
        }

    }

    @Override
    public String toString() {
        return  expression1 + " " + operator +
                " " + expression2 ;
    }
}

