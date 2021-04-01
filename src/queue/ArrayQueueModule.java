package queue;
/*

Model:
    [a1, a2...an]
    n
Inv:
    n >= 0
    forall i = 1..n: a[i] != null
*/

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueModule {
    private static int size = 0;
    private static int start = 0;
    private static Object[] elements = new Object[2];

    //Pred: el != null
    //Post: n == n' + 1 && a[n] == el && forall i = 0..n': a[i] == a'[i]
    public static void enqueue(Object e) {
        Objects.requireNonNull(e);
        ensureCapacity(size() + 1);
        elements[end()] = e;
        size++;
    }

    /*
    Pred: n > 0
    Post: R == a[n] && n == n' && forall i = 0..n: a[i] == a'[i]
    */
    public static Object element() {
        assert size() > 0;
        return elements[start];
    }

    /*
    Pred: n > 0
    Post: R == a'[n'] && n == n' - 1 && forall i = 0..n: a[i] == a'[i]
     */
    public static Object dequeue() {
        assert size() > 0;
        size--;
        Object result = elements[start];
        elements[start] = null;
        if (start + 1 >= elements.length) {
            start = 0;
        } else {
            start++;
        }
        return result;
    }

    /*
    Pred: true
    Post: R == n && n == n' && forall i = 0..n: a[i] == a'[i]
     */
    public static int size() {
        return size;
    }

    /*
    Pred: true
    Post: R == (n > 0) && n == n' && forall i = 0..n: a[i] == a'[i]
     */
    public static boolean isEmpty() {
        return size() == 0;
    }

    /*
    Pred: true
    Post: n == 0 && forall i = 0..n: a[i] == a'[i]
     */
    public static void clear() {
        Arrays.fill(elements, null);
        size = 0;
        start = 0;
    }

    /*
    Pred: el != null
    Post: n == n' + 1 && a[1] == el && forall i = 1..n': a[i + 1] == a'[i]
     */
    public static void push(Object e) {
        Objects.requireNonNull(e);
        ensureCapacity(size() + 1);
        if (start == 0) {
            start = elements.length;
        }
        elements[--start] = e;
        size++;
    }

    /*
    Pred: n > 0
    Post: R == a[n] && n == n' && forall i = 1..n: a[i] == a'[i]
    */
    public static Object peek() {
        assert size() > 0;
        if (end() == 0) {
            return elements[elements.length - 1];
        } else {
            return elements[end() - 1];
        }
    }

    /*
    Pred: n > 0
    Post: R == a'[n] && n == n' - 1 && forall i = 1..n: a[i] == a'[i]
     */
    public static Object remove() {
        assert size() > 0;
        size--;
        Object result = elements[end()];
        elements[end()] = null;
        return result;
    }

    //Pred: true
    //Post: forall i = 1..n: a[i] == a'[i]
    private static int end() {
        return (start + size) % elements.length;
    }

    //Pred: true
    //Post: forall i = 1..n: a[i] == a'[i]
    private static void ensureCapacity(final int capacity) {
        if (elements.length < capacity) {
            Object[] ob = new Object[capacity * 2];
            System.arraycopy(elements, start, ob, 0, elements.length - start);
            System.arraycopy(elements, 0, ob, elements.length - start, end());
            elements = ob;
            start = 0;
        }
    }
}
