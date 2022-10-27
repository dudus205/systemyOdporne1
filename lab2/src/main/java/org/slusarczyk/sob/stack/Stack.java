package org.slusarczyk.sob.stack;

import org.slusarczyk.sob.stack.exception.EmptyStackException;
import org.slusarczyk.sob.stack.exception.InvalidStackCapacityException;

import java.lang.reflect.Array;

public class Stack<T> {

    private T[] elementData;
    private int capacity;
    private int top;
    private boolean dirty;

    public Stack(Class<T> type, int capacity) throws InvalidStackCapacityException {
        validate(capacity);
        this.capacity = capacity;
        this.elementData = (T[]) Array.newInstance(type, capacity);
        this.top = -1;
    }

    private void validate(int capacity) throws InvalidStackCapacityException {
        if (capacity <= 0) {
            throw new InvalidStackCapacityException(capacity);
        }
    }

    private void validateDirty() throws EmptyStackException {
        if (!dirty) {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        if (!dirty) {
            return true;
        }
        return top < 0;
    }

    public T top() throws EmptyStackException {
        return pop(false);
    }

    public T pop() throws EmptyStackException {
        return pop(true);
    }

    private T pop(boolean remove) throws EmptyStackException {
        validateDirty();

        if (top >= 0) {
            return elementData[remove ? top-- : top];
        }
        throw new EmptyStackException();
    }

    public void push(T element) {
        if (!dirty) {
            this.dirty = true;
        }
        elementData[++top] = element;
    }

    public int getCapacity() {
        return capacity;
    }
}
