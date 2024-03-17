package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * A stack implementation using a singly linked list
 *
 * @author Shawn Zhang Joshua White
 * @version January 29, 2024
 */

public class LinkedListStack<T> implements Stack<T> {

    private SinglyLinkedList<T> list;

    /**
     * Constructor
     */
    public LinkedListStack(){
        list = new SinglyLinkedList<T>();
    }

    /**
     * Clears the stack
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Checks if the stack is empty
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the element at the top of the stack
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T peek() throws NoSuchElementException {
        return list.getFirst();
    }

    /**
     * Removes and returns the element at the top of the stack
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T pop() throws NoSuchElementException {
        return list.deleteFirst();
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack
     *
     * @param element - the element to be added
     */
    @Override
    public void push(T element) {
        list.insertFirst(element);
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return list.size();
    }
}
