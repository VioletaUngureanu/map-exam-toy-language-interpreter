package model.type;

import model.value.IValue;
import model.value.ReferenceValue;

public record ReferenceType(Type innerType) implements Type {

    public Type getInnerType() {
        return innerType;
    }

    @Override
    public boolean equals(Object another) {
        if (another == this) return true;
        if (!(another instanceof ReferenceType(Type type))) return false;
        return this.innerType.equals(type);
    }

    @Override
    public String toString() {
        return "Ref(" + innerType.toString() + ")";
    }

    public IValue getDefaultValue() {
        return new ReferenceValue(0, innerType);
    }
}

