package queue;

public class LinkedQueue extends AbstractQueue implements Queue {
    private Node head;

    private static class Node {
        private final Object value;
        private Node next;
        private Node prev;

        public Node(Object value, Node prev, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    protected void enqueueImpl(Object e) {
        if (head == null) {
            Node node = new Node(e, null, null);
            node.next = node;
            node.prev = node;
            head = node;
        } else {
            head.prev.next = new Node(e, head.prev, head);
            head.prev = head.prev.next;
        }
    }

    public Object element() {
        assert size > 0;
        return head.value;
    }

    protected Object dequeueImpl() {
        Object result = head.value;
        if (size == 0) {
            head = null;
            return result;
        }
        head.prev.next = head.next;
        head.next.prev = head.prev;
        head = head.next;
        return result;
    }

    protected void pushImpl(Object e) {
        if (this.head == null) {
            Node node = new Node(e, null, null);
            node.next = node;
            node.prev = node;
            this.head = node;
        } else {
            this.head.next = new Node(e, this.head, this.head.next);
            this.head.next.prev = this.head.next;
            this.head = this.head.next;
        }
    }

    public Object peek() {
        assert size > 0;

        return head.prev;
    }

    @Override
    protected Queue create() {
        return new LinkedQueue();
    }

    protected Object removeImpl() {
        Object result = this.head.prev.value;
        this.head.prev = this.head.prev.prev;
        this.head.prev.prev.next = this.head;
        return result;
    }
}
