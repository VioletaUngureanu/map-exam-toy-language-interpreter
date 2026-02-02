package model.programState;

import exceptions.LatchAlreadyDefined;
import exceptions.LatchNotDefinedException;

import java.util.HashMap;
import java.util.Map;

public class LatchTable implements ILatchTable{
    private final Map<Integer, Integer> latchTable = new HashMap<Integer, Integer>();
    private int freeLocation = 0;

    @Override
    public synchronized void put(int newLockAddress, int i) {
        if(containsKey(newLockAddress))
            throw new LatchAlreadyDefined("The latch is already defined");
        latchTable.put(newLockAddress, i);
    }

    @Override
    public synchronized int getNewFreeLocation() {
        freeLocation++;
        return freeLocation;
    }

    @Override
    public synchronized boolean containsKey(int key) {
        return latchTable.containsKey(key);
    }

    @Override
    public synchronized int lookup(int value) {
        if(!containsKey(value))
            throw new LatchNotDefinedException("The latch is not defined");
        return latchTable.get(value);
    }

    @Override
    public synchronized void update(int value, int i) {
        if(!containsKey(value))
            throw new LatchNotDefinedException("The latch is not defined");
        latchTable.put(value, i);
    }

    @Override
    public synchronized Map<Integer, Integer> getContent() {
        return latchTable;
    }
}
