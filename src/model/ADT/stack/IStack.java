package model.ADT.stack;

public interface IStack<T>{
    void push(T item);
    T pop();
    boolean isEmpty();
    T top();
    IStack<T> deepCopy();

}
