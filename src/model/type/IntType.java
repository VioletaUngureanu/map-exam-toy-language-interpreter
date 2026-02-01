package model.type;

import model.value.IValue;
import model.value.IntegerValue;

public class IntType implements Type{

    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }
    @Override
    public String toString(){ return "int";
    }

    @Override
    public IValue getDefaultValue() {
        return new IntegerValue(0);
    }
}
