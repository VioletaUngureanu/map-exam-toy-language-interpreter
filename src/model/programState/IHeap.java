package model.programState;

import model.expression.IExpression;
import model.value.IValue;

import java.util.Map;

public interface IHeap {
    int add(IValue value);
    IValue get(Integer position);
    int getFreePosition();

    boolean isDefined(int address);

    void update(int address, IValue value);

    void setContent(Map<Integer, IValue> integerIValueMap);

    Map<Integer, IValue> getContent();

}

