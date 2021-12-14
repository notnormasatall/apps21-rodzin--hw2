package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList queue = new ImmutableLinkedList();

    public Object peek() {
        return queue.getLast();
    }

    public Object dequeue() {
        Object last = this.peek();
        queue = queue.removeLast();
        return last;
    }

    public void enqueue(Object e) {
        queue = queue.addFirst(e);
    }
}
