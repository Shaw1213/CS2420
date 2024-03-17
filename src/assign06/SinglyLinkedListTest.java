package assign06;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void insertFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        assertEquals(1, list.getFirst());
    }

    @Test
    void insert() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insert(1, 2); // Insert at the end
        assertEquals(2, list.get(1));
    }

    @Test
    void getFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        assertEquals(1, list.getFirst());
    }

    @Test
    void get() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        assertEquals(1, list.get(1));
    }

    @Test
    void deleteFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        assertEquals(1, list.deleteFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void delete() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        assertEquals(1, list.delete(1));
        assertEquals(2, list.getFirst());
    }

    @Test
    void indexOf() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        assertEquals(1, list.indexOf(1));
    }

    @Test
    void size() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertEquals(0, list.size());
        list.insertFirst(1);
        assertEquals(1, list.size());
    }

    @Test
    void isEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());
        list.insertFirst(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void clear() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void toArray() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        Object[] arr = list.toArray();
        assertArrayEquals(new Object[]{2, 1}, arr);
    }

    @Test
    void iterator() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(1), it.next());
        assertFalse(it.hasNext());
    }
    @Test
    void getThrowsExceptionWhenInvalidIndex() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void deleteThrowsExceptionWhenInvalidIndex() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1));
    }

    @Test
    void getFirstThrowsExceptionWhenEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void deleteFirstThrowsExceptionWhenEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::deleteFirst);
    }
}