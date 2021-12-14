package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.immutable.Node;

import static org.junit.Assert.*;

public class NodeTest {
    Node node;
    Node next;

    @Before
    public void setUp() {
        node = new Node();
        next = new Node();
        node.setValue(10);
        next.setValue(12);
        node.setNext(next);
        next.setPrevious(node);
    }

    @Test
    public void getVal() {
        assertEquals(10, node.getValue());
    }

    @Test
    public void getNxt() {
        assertEquals(next, node.getNext());
    }

    @Test
    public void getPrev() {
        assertEquals(node, next.getPrevious());
    }

    @Test
    public void toStr() {
        assertEquals("10", node.toString());
    }
}