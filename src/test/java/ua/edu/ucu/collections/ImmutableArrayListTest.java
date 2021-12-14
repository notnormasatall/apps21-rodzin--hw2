package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.edu.ucu.collections.immutable.ImmutableArrayList;
import ua.edu.ucu.collections.immutable.ImmutableList;

import java.util.InputMismatchException;


public class ImmutableArrayListTest {
    ImmutableArrayList arrList;
    Object[] array;

    @Before
    public void setUp() {
        array = new Object[]{1,2,3,4,5};
        arrList = new ImmutableArrayList(array);
    }

    @Test
    public void empty() {
        assertEquals(5, arrList.size());
        assertEquals(false, arrList.isEmpty());
    }

    @Test
    public void toClear() {
        assertEquals(0, arrList.clear().size());
    }

    @Test
    public void idx() {
        assertEquals(2, arrList.indexOf(3));
    }

    @Test (expected = InputMismatchException.class)
    public void idxErrors() {
        arrList.indexOf(10);
    }

    @Test (expected = InputMismatchException.class)
    public void idxErrorsZero() {
        arrList.indexOf(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setErrors() {
        arrList.set(10, 15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setErrorsZero() {
        arrList.set(-1, 15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeErrors() {
        arrList.remove(15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeErrorsZero() {
        arrList.remove(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getErrors() {
        arrList.get(15);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getErrorsZero() {
        arrList.get(-1);
    }


    @Test
    public void toSet() {
        ImmutableList arr = arrList.set(2,2);
        assertEquals(2, arr.get(2));
    }

    @Test
    public void toRemove() {
        ImmutableList arr = arrList.remove(0);
        assertEquals(4, arr.size());
    }

    @Test
    public void toGet() {
        assertEquals(1, arrList.get(0));
    }

    @Test
    public void toAddAll() {
        Object[] adder = new Object[]{6,7};

        assertEquals(7,arrList.addAll(adder).size());
        assertEquals(7,arrList.addAll(adder).get(6));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void toAddAllErr() {
        arrList.addAll(15, new Object[]{});
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void toAddAllErrZero() {
        arrList.addAll(-1, new Object[]{});
    }

    @Test
    public void toAdd() {
        int num = 1;

        assertEquals(6,arrList.add(1).size());
        assertEquals(1, arrList.add(2, 1).get(2));
    }

    @Test
    public void toAddAllIdx() {
        Object[] adder = new Object[]{6,7};

        assertEquals(6, arrList.addAll(2, adder).get(2));
        assertEquals(7, arrList.addAll(2, adder).get(3));
    }

    @Test
    public void checkAttributes() {
        ImmutableArrayList emp = new ImmutableArrayList();
        assertEquals(5, arrList.size());
        assertEquals(0, emp.size());
        assertEquals(0, emp.toArray().length);
    }
}
