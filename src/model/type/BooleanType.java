package model.type;

import model.value.BooleanValue;
import model.value.IValue;

public class BooleanType implements Type {


    @Override
    public boolean equals(Object another) {
        return another instanceof BooleanType;
    }
    @Override
    public String toString(){ return "bool";
    }

    @Override
    public IValue getDefaultValue() {
        return new BooleanValue(false);
    }
}
