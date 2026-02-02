package model.programState;


import java.util.Map;

public interface ILatchTable {

    void put(int newLockAddress, int i);


    int getNewFreeLocation();

    boolean containsKey(int value);

    int lookup(int value);

    void update(int value, int i);

    Map<Integer, Integer> getContent();
}
