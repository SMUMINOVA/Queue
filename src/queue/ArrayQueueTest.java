package queue;

public class ArrayOOPQueueTest {
    public static void fill_start(ArrayQueue stack) {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
    }

    public static void fill_end(ArrayQueue stack) {
        for (int i = 0; i < 10; i++) {
            stack.enqueue(i);
        }
    }

    public static void dump_start(ArrayQueue stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.size() + " " + stack.element());
            stack.dequeue();
        }
    }

    public static void dump_end(ArrayQueue stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.size() + " " + stack.peek());
            stack.remove();
        }
    }

    public static void main(String[] args) {
        ArrayQueue stack = new ArrayQueue();
        fill_start(stack);
        dump_start(stack);
        fill_end(stack);
        dump_end(stack);
        fill_end(stack);
        System.out.println("size before method clear: " + stack.size());
        stack.clear();
        System.out.println("size after method clear: " + stack.size());
    }
}
