package model.programState;


import model.ADT.list.List;
import model.value.IValue;

public class Out implements IOut {
    private final List<IValue> output = new List<>();



    @Override
    public synchronized void append(IValue value) {
        output.add(value);
    }

    @Override
    public synchronized String toString() {
        return output.toString();
    }
}
