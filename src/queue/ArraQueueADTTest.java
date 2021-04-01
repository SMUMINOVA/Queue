package queue;

public class ArraQueueADTTest {
    public static void fill_start(ArrayQueueADT stack) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.push(stack, i);
        }
    }

    public static void fill_end(ArrayQueueADT stack) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(stack, i);
        }
    }

    public static void dump_start(ArrayQueueADT stack) {
        while (!ArrayQueueADT.isEmpty(stack)) {
            System.out.println(ArrayQueueADT.size(stack) + " " + ArrayQueueADT.element(stack));
            ArrayQueueADT.dequeue(stack);
        }
    }

    public static void dump_end(ArrayQueueADT stack) {
        while (!ArrayQueueADT.isEmpty(stack)) {
            System.out.println(ArrayQueueADT.size(stack) + " " + ArrayQueueADT.peek(stack));
            ArrayQueueADT.remove(stack);
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT stack = ArrayQueueADT.create();
        fill_start(stack);
        dump_start(stack);
        fill_end(stack);
        dump_end(stack);
        fill_end(stack);
        System.out.println("size before method clear: " + ArrayQueueADT.size(stack));
        ArrayQueueADT.clear(stack);
        System.out.println("size after method clear: " + ArrayQueueADT.size(stack));
    }
}
