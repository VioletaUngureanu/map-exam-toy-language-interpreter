package model.value;

import model.type.IntType;
import model.type.Type;


public record IntegerValue(int value) implements IValue {

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public IValue deepCopy() {
        return new IntegerValue(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }



}
