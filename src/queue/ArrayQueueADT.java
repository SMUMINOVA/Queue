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

public class ArrayQueueADT {
    private int size = 0;
    private int start = 0;
    private Object[] elements = new Object[2];

    /*
    Pred: true
    Post: R.n == 0 && R new
     */
    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    //Pred: el != null && queue != null
    //Post: n == n' + 1 && a[n] == el && forall i = 0..n': a[i] == a'[i]
    public static void enqueue(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(e);
        Objects.requireNonNull(queue);
        ensureCapacity(queue, size(queue) + 1);
        queue.elements[end(queue)] = e;
        queue.size++;
    }

    /*
    Pred: n > 0 && queue != null
    Post: R == a[n] && n == n' && forall i = 0..n: a[i] == a'[i]
    */
    public static Object element(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;
        return queue.elements[queue.start];
    }

    /*
    Pred: n > 0  && queue != null
    Post: R == a'[n'] && n == n' - 1 && forall i = 0..n: a[i] == a'[i]
     */
    public static Object dequeue(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;
        queue.size--;
        Object result = queue.elements[queue.start];
        queue.elements[queue.start] = null;
        if (queue.start + 1 >= queue.elements.length) {
            queue.start = 0;
        } else {
            queue.start++;
        }
        return result;
    }

    /*
    Pred: queue != null
    Post: R == n && n == n' && forall i = 0..n: a[i] == a'[i]
     */
    public static int size(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        return queue.size;
    }

    /*
    Pred: queue != null
    Post: R == (n > 0) && n == n' && forall i = 0..n: a[i] == a'[i]
     */
    public static boolean isEmpty(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        return size(queue) == 0;
    }

    /*
    Pred: queue != null
    Post: n == 0 && forall i = 0..n: a[i] == a'[i]
     */
    public static void clear(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        Arrays.fill(queue.elements, null);
        queue.size = 0;
        queue.start = 0;
    }

    /*
    Pred: el != null && queue != null
    Post: n == n' + 1 && a[1] == el && forall i = 1..n': a[i + 1] == a'[i]
     */
    public static void push(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(e);
        Objects.requireNonNull(queue);
        ensureCapacity(queue, size(queue) + 1);
        if (queue.start == 0) {
            queue.start = queue.elements.length;
        }
        queue.elements[--queue.start] = e;
        queue.size++;
    }

    /*
    Pred: n > 0 && queue != null
    Post: R == a[n] && n == n' && forall i = 1..n: a[i] == a'[i]
    */
    public static Object peek(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;
        if (end(queue) == 0) {
            return queue.elements[queue.elements.length - 1];
        } else {
            return queue.elements[end(queue) - 1];
        }
    }

    /*
    Pred: n > 0 && queue != null
    Post: R == a'[n] && n == n' - 1 && forall i = 1..n: a[i] == a'[i]
     */
    public static Object remove(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;
        queue.size--;
        Object result = queue.elements[end(queue)];
        queue.elements[end(queue)] = null;
        return result;
    }

    //Pred: true && queue != null
    //Post: forall i = 1..n: a[i] == a'[i]
    private static int end(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        return (queue.start + queue.size) % queue.elements.length;
    }

    //Pred: queue != null
    //Post: forall i = 1..n: a[i] == a'[i]
    private static void ensureCapacity(ArrayQueueADT queue, final int capacity) {
        Objects.requireNonNull(queue);
        if (queue.elements.length < capacity) {
            Object[] ob = new Object[capacity * 2];
            System.arraycopy(queue.elements, queue.start, ob, 0, queue.elements.length - queue.start);
            System.arraycopy(queue.elements, 0, ob, queue.elements.length - queue.start, end(queue));
            queue.elements = ob;
            queue.start = 0;
        }
    }
}
