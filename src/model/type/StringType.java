package model.type;

import model.value.IValue;
import model.value.StringValue;

public class StringType implements Type{
    @Override
    public boolean equals(Object another) {
        if (another instanceof  StringType)
            return true;
        return false;
    }
    @Override
    public String toString(){ return "string";
    }

    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }
}
