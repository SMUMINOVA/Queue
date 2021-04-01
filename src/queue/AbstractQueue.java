package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(Object e) {
        add(e);
        enqueueImpl(e);
    }

    public Object dequeue() {
        delete();
        return dequeueImpl();
    }

    public void push(Object e) {
        add(e);
        pushImpl(e);
    }

    public Object remove() {
        delete();
        return removeImpl();
    }

    private void delete(){
        assert this.size > 0;
        this.size--;
    }

    private void add(Object e) {
        Objects.requireNonNull(e);
        this.size++;
    }

    public boolean contains(Object el) {
        return findElement(el, false);
    }

    public boolean removeFirstOccurrence(Object e) {
        return findElement(e, true);
    }

    protected boolean findElement(Object e, boolean delete) {
        Objects.requireNonNull(e);
        Queue queue = create();
        boolean exist = false;
        while (!isEmpty()) {
            Object ob = dequeue();
            if (ob.equals(e)) {
                exist = true;
                if (delete) {
                    delete = false;
                } else {
                    queue.enqueue(ob);
                }
            } else {
                queue.enqueue(ob);
            }
        }
        while (!queue.isEmpty()) {
            enqueue(queue.dequeue());
        }
        return exist;
    }

    public void clear(){
        while (!isEmpty()) {
            dequeue();
        }
    }

    protected abstract Queue create();

    protected abstract Object removeImpl();

    protected abstract void pushImpl(Object e);

    protected abstract Object dequeueImpl();

    protected abstract void enqueueImpl(Object e);

    public abstract Object element();

    public abstract Object peek();
}
