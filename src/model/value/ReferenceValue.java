package model.value;

import model.type.ReferenceType;
import model.type.Type;

public record ReferenceValue(int heapAddress,
                             Type innerType) implements IValue {

    @Override
    public Type getType() {
        return new ReferenceType(innerType);
    }

    @Override
    public IValue deepCopy() {
        return new ReferenceValue(this.heapAddress, this.innerType);
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", heapAddress, innerType.toString());
    }
    public Type getInnerType() {
        return innerType;
    }
}
