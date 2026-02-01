package model.programState;

import exceptions.MemoryViolationException;
import model.ADT.dictionary.Dictionary;
import model.ADT.dictionary.IDictionary;
import model.expression.IExpression;
import model.value.IValue;

import java.util.Map;

public class Heap implements IHeap {
    private final IDictionary<Integer, IValue> heap = new Dictionary<>();
    private int firstFreeLocation = 1;

    private synchronized int newFreeLocation() {
        do firstFreeLocation += 1;
        while (firstFreeLocation == 0 || heap.containsKey(firstFreeLocation));
        return firstFreeLocation;
    }

    @Override
    public synchronized int add(IValue value) {
        heap.add(firstFreeLocation, value);
        int valueLocation = firstFreeLocation;
        firstFreeLocation = newFreeLocation();
        return valueLocation;
    }

    @Override
    public synchronized IValue get(Integer position) {
        if (!heap.containsKey(position))
            throw new MemoryViolationException("The memory you are trying to access is not allocated");
        return heap.getValueForKey(position);
    }

    @Override
    public synchronized int getFreePosition() {
        return firstFreeLocation;
    }

    @Override
    public synchronized boolean isDefined(int address) {
        return heap.containsKey(address);
    }

    @Override
    public synchronized void update(int address, IValue value) {
        if (!heap.containsKey(address))
            throw new MemoryViolationException("The memory you are trying to access is not allocated");
        heap.add(address, value);
    }

    @Override
    public synchronized void setContent(Map<Integer, IValue> integerIValueMap) {
        heap.clear();
        for( int i: integerIValueMap.keySet()){
            heap.add(i, integerIValueMap.get(i));
        }
    }

    @Override
    public synchronized Map<Integer, IValue> getContent() {
        return new java.util.HashMap<>(heap.getContent());
    }


    @Override
    public synchronized String toString() {
        return "" +
                heap;
    }
}
