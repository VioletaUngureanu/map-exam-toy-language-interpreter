package model.ADT.list;

public interface IList<T> {
    void add(T item);
    T get(int index);
    int size();
    void remove(int index);;
    IList<T> deepCopy();
}
