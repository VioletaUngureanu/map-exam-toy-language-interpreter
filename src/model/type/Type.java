package model.type;

import model.value.IValue;

public interface Type {
    boolean equals(Object another);
    String toString();
    IValue getDefaultValue();
}
