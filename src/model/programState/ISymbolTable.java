package model.programState;


import model.type.Type;
import model.value.IValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public interface ISymbolTable {
    boolean isDefined(String variableName);
    void declareVariable(String variableName, Type type);
    Type getVariableType(String variableName);
    void updateValue(String variableName, IValue value);

    IValue lookup(String variableName);

    Collection<IValue> getAllValues();



}


