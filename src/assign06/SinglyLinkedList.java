package assign06;

import javax.swing.text.Element;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly linked list implementation of the List interface
 *
 * @author Shawn Zhang Joshua White
 * @version January 29, 2024
 */

public class SinglyLinkedList<T> implements List<T> {

    int size = 0;
    //Node structure
    private class Node{
        public T value;
        public Node next;

        public Node(T value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Iterator for the SinglyLinkedList class
     */
    public class ListIterator implements Iterator<T>{
        Node current = new Node(null, root);
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            T value = current.next.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            current = current.next;
            size--;
        }
    }

    //Reference to first node in chain
    private Node root;

    /**
     * Constructor
     */
    public SinglyLinkedList(){
        root = null;
    }

    /**
     * Inserts an element at the beginning of the list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(T element) {
        root = new Node(element, root);
        size++;
    }

    /**
     * Inserts an element at a specific position in the list.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        if(index == 0){
            insertFirst(element);
            return;
        }

        Node target = root;
        for(int i = 1; i < index; i++){
            if(target.next != null){
                target = target.next;
            }else throw new IndexOutOfBoundsException();
        }

        target.next = new Node(element, target.next);
        size++;
    }

    /**
     * Gets the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T getFirst() throws NoSuchElementException {
        if(root != null) return root.value;
        throw new NoSuchElementException();
    }

    /**
     * Gets the element at a specific position in the list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || root == null) {
            throw new IndexOutOfBoundsException();
        }

        Node current = root;
        for (int i = 0; i < index; i++) {
            if (current.next != null) {
                current = current.next;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        return current.value;
    }

    /**
     * Deletes and returns the first element from the list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T deleteFirst() throws NoSuchElementException {
        T value;
        if(root != null) {
            value = root.value;
            root = root.next;
            size--;
            return value;
        }
        else throw new NoSuchElementException();

    }

    /**
     * Deletes and returns the element at a specific position in the list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        Node target = new Node(null,root);
        for(int i = 0; i < index; i++){
            if(target.next != null){
                target = target.next;
            }else throw new IndexOutOfBoundsException();
        }

        if(target.next == null) throw new IndexOutOfBoundsException();

        T value = target.next.value;
        target.next = target.next.next;
        size--;
        return value;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list
     * or -1 if this list does not contain the element.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence of the specified element in the list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(T element) {
        if (isEmpty()) return -1;

        Node test = root;
        int index = 0;

        while (test.value != element){
            if(test.next == null) return -1;
            test = test.next;
            index++;
        }

        return index;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all the elements from the list.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node current = root;
        int i = 0;
        while(current != null){
            result[i++] = current.value;
            current = current.next;
        }
        return result;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }
}
