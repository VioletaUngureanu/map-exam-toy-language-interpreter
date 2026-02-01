package model.programState;

import model.ADT.dictionary.Dictionary;
import model.ADT.dictionary.IDictionary;
import model.type.Type;
import model.value.IValue;
import exceptions.VariableAlreadyDefined;
import exceptions.VariableNotDefinedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class SymbolTable implements ISymbolTable {
    private final IDictionary<String, IValue> symbolTable = new Dictionary<>();


    @Override
    public boolean isDefined(String variableName) {
        return symbolTable.isDefined(variableName);
    }

    @Override
    public void declareVariable(String variableName, Type type) {
        if (isDefined(variableName)) {
            throw new VariableAlreadyDefined("Variable " + variableName + " is already defined");
        }
        symbolTable.add(variableName, type.getDefaultValue());
    }

    @Override
    public Type getVariableType(String variableName) {
        if (!isDefined(variableName)) {;
            throw new VariableNotDefinedException("Variable " + variableName + " is not defined");
        }
        return symbolTable.getValueForKey(variableName).getType();
    }

    @Override
    public void updateValue(String variableName, IValue value) {
        if (!isDefined(variableName)) {;
            throw new VariableNotDefinedException("Variable " + variableName + " is not defined");
        }
        symbolTable.add(variableName, value);
    }

    @Override
    public IValue lookup(String variableName) {
        if (!isDefined(variableName)) {;
            throw new VariableNotDefinedException("Variable " + variableName + " is not defined");
        }
        return symbolTable.getValueForKey(variableName);
    }

    @Override
    public Collection<IValue> getAllValues() {
        return symbolTable.getAllValues();
    }

    public Collection<String> getAllKeys() { return symbolTable.getAllKeys(); }


    public SymbolTable deepCopy() {
        SymbolTable copy = new SymbolTable();
        for (String key : symbolTable.getAllKeys()) {
            IValue value = symbolTable.getValueForKey(key);
            copy.declareVariable(key, value.getType());
            copy.updateValue(key, value.deepCopy());
        }
        return copy;

    }


    @Override
    public String toString() {
        return symbolTable.toString();
    }
}
