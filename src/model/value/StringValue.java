package model.value;

import model.type.StringType;
import model.type.Type;

public record StringValue(String value) implements IValue {


    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
