package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.InputMismatchException;

public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private final int length;

    public ImmutableLinkedList(Object[] elements) {
        this.head = null;
        this.tail = null;
        this.length = elements.length;

        Node current = null;

        for (int i = 0; i < elements.length; i++) {
            Node newNode = new Node();
            newNode.setValue(elements[i]);

            if (current != null) {
                current.setNext(newNode);
            }
            newNode.setPrevious(current);
            current = newNode;

            if (i == 0) {
                this.head = newNode;
            }

            if (i == elements.length - 1) {
                this.tail = newNode;
            }
        }
    }

    public ImmutableLinkedList() {
        this.length = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public ImmutableList add(Object e) {
        Object[] toAdd = new Object[]{e};
        return this.addAll(toAdd);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] toAdd = new Object[]{e};
        return this.addAll(index, toAdd);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return this.addAll(this.length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] oldArr = Arrays.copyOf(this.toArray(), this.length);
        Object[] extended = new Object[this.length + c.length];

        for (int i = 0; i < extended.length; i++) {
            if (i < index) {
                extended[i] = oldArr[i];
            } else if (i < index + c.length) {
                extended[i] = c[i - index];
            } else {
                extended[i] = oldArr[i - c.length];
            }
        }
        return new ImmutableLinkedList(extended);
    }

    @Override
    public Object get(int index) {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node current = this.head;
        int idx = 0;

        while (idx != index) {
            if (current == null) {
                break;
            }
            current = current.getNext();
            idx += 1;
        }
        return current.getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] removed = new Object[this.length - 1];
        Object[] arr = Arrays.copyOf(this.toArray(), this.length);

        int j = 0;
        for (int i = 0; i < this.length; i++) {
            if (i == index) {
                continue;
            }
            removed[j] = arr[i];
            j += 1;
        }
        return new ImmutableLinkedList(removed);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] changed = Arrays.copyOf(this.toArray(), this.length);
        changed[index] = e;
        return new ImmutableLinkedList(changed);
    }

    @Override
    public int indexOf(Object e) {
        int idx = 0;
        Node current = this.head;

        while (current != null) {
            if (current.getValue() == e) {
                return idx;
            }
            current = current.getNext();
            idx += 1;
        }

        throw new InputMismatchException();
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.length];
        Node current = this.head;
        int idx = 0;

        while (current != null) {
            arr[idx] = current.getValue();
            current = current.getNext();
            idx += 1;
        }
        return arr;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) this.addAll(0, new Object[]{e});
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) this.addAll(this.length, new Object[]{e});
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public Object getFirst() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return head.getValue();
    }

    public Object getLast() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) this.remove(this.length - 1);
    }
}
