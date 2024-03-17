package assign06;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {

    @Test
    void clear() {
        var stack = new LinkedListStack<Integer>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.clear();
        assertEquals(stack.size(),0);
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmpty() {
        var stack = new LinkedListStack<Integer>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void peek() {
        var stack = new LinkedListStack<Integer>();
        stack.push(5);
        stack.push(4);
        assertEquals(stack.peek(), 4);
    }

    @Test
    void pop() {
        var stack = new LinkedListStack<Integer>();
        stack.push(5);
        stack.push(4);
        assertEquals(stack.pop(), 4);
        assertEquals(stack.pop(), 5);
    }

    @Test
    void size() {
        var stack = new LinkedListStack<Integer>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        assertEquals(stack.size(),3);
    }

    @Test
    void isEmptyReturnsFalseWhenNotEmpty() {
        var stack = new LinkedListStack<Integer>();
        stack.push(5);
        assertFalse(stack.isEmpty());
    }

    @Test
    void peekThrowsExceptionWhenEmpty() {
        var stack = new LinkedListStack<Integer>();
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void popThrowsExceptionWhenEmpty() {
        var stack = new LinkedListStack<Integer>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void pushAddsElementToStack() {
        var stack = new LinkedListStack<Integer>();
        stack.push(5);
        assertEquals(5, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void pushWorksWhenStackIsFull() {
        var stack = new LinkedListStack<Integer>();
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }
        assertEquals(1000, stack.size());
        assertEquals(999, stack.peek());
    }

    @Test
    void popAndPeekMaintainOrder() {
        var stack = new LinkedListStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void clearWorksWhenEmpty() {
        var stack = new LinkedListStack<Integer>();
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    void sizeAfterMultiplePushAndPop() {
        var stack = new LinkedListStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.push(3);
        assertEquals(2, stack.size());
    }
}