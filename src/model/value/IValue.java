package model.value;

import model.type.Type;


public interface IValue {
    Type getType();
    IValue deepCopy();
    boolean equals(Object another);
}
