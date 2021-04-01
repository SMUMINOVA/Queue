package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueue extends AbstractQueue {
    private int start = 0;
    private Object[] elements = new Object[2];

    @Override
    protected void enqueueImpl(Object e) {
        ensureCapacity(size() + 1);
        int end = end() - 1;
        if (end < 0) {
            end = elements.length - 1;
        }
        elements[end] = e;
    }

    public Object element() {
        assert size() > 0;

        return elements[start];
    }

    public Object peek() {
        assert size() > 0;

        if (end() == 0) {
            return elements[elements.length - 1];
        } else {
            return elements[end() - 1];
        }
    }

    @Override
    protected Queue create() {
        return new ArrayQueue();
    }

    @Override
    protected Object removeImpl() {
        int end = end();
        if (end < 0) {
            end = elements.length - 1;
        }
        Object result = elements[end];
        elements[end] = null;
        return result;
    }

    @Override
    protected void pushImpl(Object e) {
        ensureCapacity(size() + 1);
        if (start == 0) {
            start = elements.length;
        }
        elements[--start] = e;
    }

    @Override
    protected Object dequeueImpl() {
        Object result = elements[start];
        elements[start] = null;
        if (start + 1 >= elements.length) {
            start = 0;
        } else {
            start++;
        }
        return result;
    }

    //Pred: true
    //Post: forall i = 1..n: a[i] == a'[i]
    private int end() {
        return (start + size) % elements.length;
    }

    //Pred: true
    //Post: forall i = 1..n: a[i] == a'[i]
    private void ensureCapacity(final int capacity) {
        if (this.elements.length < capacity) {
            Object[] ob = new Object[capacity * 2];
            System.arraycopy(this.elements, this.start, ob, 0, this.elements.length - this.start);
            System.arraycopy(this.elements, 0, ob, this.elements.length - this.start, this.end());
            this.elements = ob;
            this.start = 0;
        }
    }
}
