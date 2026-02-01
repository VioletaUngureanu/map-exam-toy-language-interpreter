package model.value;

import model.type.BooleanType;
import model.type.Type;


public record BooleanValue(boolean value) implements IValue {

    @Override
    public Type getType(){
        return new BooleanType();
    }

    @Override
    public IValue deepCopy() {
        return new BooleanValue(value);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }


}
