package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.immutable.ImmutableArrayList;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.immutable.ImmutableList;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;

public class ImmutableLinkedListTest {
    ImmutableLinkedList linkedList;
    Object[] array;

    @Before
    public void setUp() {
        array = new Object[]{1,2,3,4,5};
        linkedList = new ImmutableLinkedList(array);
    }

    @Test
    public void empty() {
        assertEquals(5, linkedList.size());
        assertEquals(false, linkedList.isEmpty());
    }

    @Test
    public void toClear() {
        assertEquals(0, linkedList.clear().size());
    }

    @Test
    public void idx() {
        assertEquals(2, linkedList.indexOf(3));
    }

    @Test (expected = InputMismatchException.class)
    public void idxErrors() {
        linkedList.indexOf(10);
    }

    @Test (expected = InputMismatchException.class)
    public void idxErrorsZero() {
        linkedList.indexOf(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setErrors() {
        linkedList.set(10, 15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setErrorsZero() {
        linkedList.set(-1, 15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeErrors() {
        linkedList.remove(15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeErrorsZero() {
        linkedList.remove(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getErrors() {
        linkedList.get(15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getErrorsZero() {
        linkedList.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void toAddAllErr() {
        linkedList.addAll(15, new Object[]{});
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void toAddAllErrZero() {
        linkedList.addAll(-1, new Object[]{});
    }

    @Test
    public void toSet() {
        ImmutableList arr = linkedList.set(2,2);
        assertEquals(2, arr.get(2));
    }

    @Test
    public void toRemove() {
        ImmutableList arr = linkedList.remove(0);
        assertEquals(4, arr.size());
    }

    @Test
    public void toGet() {
        assertEquals("1", linkedList.get(0).toString());
    }

    @Test
    public void toAddAll() {
        Object[] adder = new Object[]{6,7};

        assertEquals(7,linkedList.addAll(adder).size());
        assertEquals(7,linkedList.addAll(adder).get(6));
    }

    @Test
    public void toAdd() {
        int num = 1;

        assertEquals(6,linkedList.add(1).size());
        assertEquals(1, linkedList.add(2, 1).get(2));
    }

    @Test
    public void toAddAllIdx() {
        Object[] adder = new Object[]{6,7};

        assertEquals(6, linkedList.addAll(2, adder).get(2));
        assertEquals(7, linkedList.addAll(2, adder).get(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void addErrors() {
        linkedList.addAll(15, new Object[]{2});
    }

    @Test
    public void checkAttributes() {
        ImmutableArrayList emp = new ImmutableArrayList();
        assertEquals(5, linkedList.size());
        assertEquals(0, emp.size());
        assertEquals(0, emp.toArray().length);
        assertEquals(1, linkedList.getFirst());
        assertEquals(5, linkedList.getLast());
        assertEquals(1, linkedList.getHead().getValue());
        assertEquals(5, linkedList.getTail().getValue());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void firstErr() {
        ImmutableLinkedList lst = new ImmutableLinkedList();
        lst.getFirst();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void lastErr() {
        ImmutableLinkedList lst = new ImmutableLinkedList();
        lst.getLast();
    }

    @Test
    public void remover() {
        assertEquals(4, linkedList.removeLast().getLast());
        assertEquals(2, linkedList.removeFirst().getFirst());
    }

    @Test
    public void adder() {
        assertEquals(6, linkedList.addLast(6).getLast());
        assertEquals(0, linkedList.addFirst(0).getFirst());
    }
}
