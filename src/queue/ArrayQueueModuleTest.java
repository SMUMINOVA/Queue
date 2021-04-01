package queue;

public class ArrayQueueModuleTest {
    public static void fill_start() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.push(i);
        }
    }

    public static void fill_end() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump_start() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " + ArrayQueueModule.element());
            ArrayQueueModule.dequeue();
        }
    }

    public static void dump_end() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " + ArrayQueueModule.peek());
            ArrayQueueModule.remove();
        }
    }

    public static void main(String[] args) {
        //ArrayQueueModule.enqueue(null);
        fill_start();
        dump_start();
        fill_end();
        dump_end();
        fill_end();
        System.out.println("size before method clear: " + ArrayQueueModule.size());
        ArrayQueueModule.clear();
        System.out.println("size after method clear: " + ArrayQueueModule.size());
    }
}
