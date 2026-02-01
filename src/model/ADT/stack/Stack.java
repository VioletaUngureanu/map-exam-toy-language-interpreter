package model.ADT.stack;

import exceptions.StackEmptyException;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Stack<T> implements IStack<T> {
    private ConcurrentLinkedDeque<T> stack =  new ConcurrentLinkedDeque<>();


    @Override
    public void push(T item) {
        stack.push(item);
    }

    @Override
    public T pop() throws StackEmptyException {
        T value = stack.poll();
        if (value == null) {
            throw new StackEmptyException("Stack is empty. Cannot pop element.");
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public T top() throws StackEmptyException {
        T value = stack.peek();
        if (value == null) {
            throw new StackEmptyException("Stack is empty. Cannot pop element.");
        }
        return value;
    }

    @Override
    public IStack<T> deepCopy() {
        Stack<T> newStack = new Stack<>();
        Iterator<T> it = stack.descendingIterator();
        while (it.hasNext()) {
            newStack.push(it.next());
        }

        return newStack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : stack) {
                sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
