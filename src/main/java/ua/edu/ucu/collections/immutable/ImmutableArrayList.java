package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] elems;
    private final int length;

    public ImmutableArrayList(Object[] elements) {
        this.elems = elements;
        this.length = elements.length;
    }

    public ImmutableArrayList() {
        this.elems = new Object[]{};
        this.length = 0;
    }

    @Override
    public ImmutableList add(Object e) {
        return this.addAll(new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return this.addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return this.addAll(this.length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {

        Object[] oldArr = this.toArray();
        Object[] extended = new Object[this.length + c.length];

        for (int i = 0; i < extended.length; i++) {
            if (i < index) {
                extended[i] = oldArr[i];
            } else if (index + c.length > i) {
                extended[i] = c[i - index];
            } else {
                extended[i] = oldArr[i - c.length];
            }
        }

        return new ImmutableArrayList(extended);
    }

    @Override
    public Object get(int index) {
        if (index > this.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = this.toArray();
        return arr[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (index > this.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] oldArr = this.toArray();
        Object[] removed = Arrays.copyOf(this.toArray(), this.length - 1);
        int j = 0;

        for (int i = 0; i < this.length; i++) {
            if (i == index) {
                continue;
            }
            removed[j] = oldArr[i];
            j += 1;
        }
        return new ImmutableArrayList(removed);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index > this.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] arr = this.toArray();
        arr[index] = e;
        return new ImmutableArrayList(arr);
    }

    @Override
    public int indexOf(Object e) {
        Object[] arr = this.toArray();

        for (int i = 0; i < this.length; i++) {
            if (arr[i] == e) {
                return i;
            }
        }

        throw new InputMismatchException();
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elems, this.length);
    }
}
