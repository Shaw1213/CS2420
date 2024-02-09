package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
/**
 * A priority queue that supports access of the maximum element only.
 *
 * @version January 27, 2022
 * @author Shawn Zhang and Joshua White
 *
 * @param <E> - the type of elements contained in this priority queue
 */

public class SimplePriorityQueue<E> implements PriorityQueue<E>{

    private E[] array;//The array that holds the elements
    private int size;//The size of the array
    private Comparator<? super E> comparator;//The comparator that is used to order the elements in priority queue

    //The constructor for assign03.SimplePriorityQueue with natural ordering
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(){
        array = (E[]) new Object[16];
        size = 0;
        this.comparator = null; // Natural ordering
    }

    //The constructor for assign03.SimplePriorityQueue with a comparator
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp){
        array = (E[]) new Object[16];
        size = 0;
        this.comparator = cmp;
    }
    //This is Constructor Overloading, if a comparator is not provided,
    // then the natural ordering is used(when calling assign03.SimplePriorityQueue())
    //Otherwise the comparator is used(when calling assign03.SimplePriorityQueue(Comparator<? super E> cmp))

    //Private helper methods

    /**
     * Compares a and b using the queue's comparator, if it exists, or using the natural ordering if it does not
     * @return the integer result of comparison
     */
    @SuppressWarnings("unchecked")
    private int compare(E a, E b){
        if(comparator != null){
            return comparator.compare(a,b);
        }else{
            return ((Comparable<? super E>)a).compareTo(b);
        }
    }

    /**
     * Performs a binary search on the array
     * @param target the item to search for
     * @return the index of an equivalent item, if found, or the index where the given item would be inserted, if not
     */
    private int binarySearch(E target){
        int low = 0;
        int high = size-1;
        int center;

        while(low <= high){
            //Find the center of our current chunk
            center = (low + high) / 2;

            int compare = compare(array[center], target);

            //If we undershot, compare will be > 0, so our new chunk is from low to center
            if(compare > 0) high = center - 1;
                //If we overshot, compare will be < 0, so our new chunk is from center to high
            else if(compare < 0) low = center + 1;
                //If this element matches, return the index
            else return center;
        }
        return low;
    }

    /**
     * Doubles the size of the array, without altering its contents.
     */
    @SuppressWarnings("unchecked")
    private void growArray(){
        E[] newArray = (E[])new Object[array.length*2];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /**
     * Inserts an item into the array, shifting all subsequent elements forward by one to make room.
     * Calls growArray to accommodate the new element, if necessary.
     */
    private void insertSingle(E item, int index){
        if(size + 1 >= array.length) growArray();
        for(int i = ++size; i > index; i--){
            array[i] = array[i-1];
        }
        array[index] = item;
    }

    //Public methods

    @Override
    public E findMax() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        return array[size-1]; //Size represents the size of the *used* portion of the array (The size of the queue).
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        E maxElement = array[size-1];
        array[size-1] = null;
        size--;
        return maxElement;
    }

    @Override
    public void insert(E item) {
        //Based on a TA's comment on Piazza, we are immediately throwing a NullPointerException
        // if a user tries to add (or lookup) a null item.
        if(item == null) throw new NullPointerException();

        int index = binarySearch(item);
        insertSingle(item, index);
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
        //Refuse to insert the whole collection if ANY element is invalid
        // rather than just skipping invalid and inserting valid elements.
        //This seems to be one of the typical behaviors for merging collections in java
        for(var element : coll){
            if(element == null) throw new NullPointerException();
        }

        //Running time might be reduced by growing the array to the maximum size in one step
        // instead of lazily growing it each time a power of 2 is hit.
        //Both methods should be O(n), though so it's not important for our purposes.

        for(var element : coll){
            insert(element);
        }
    }

    @Override
    public boolean contains(E item) {
        if(item == null) throw new NullPointerException();

        int index = binarySearch(item);
        //Using .equals instead of == means that equivalent but non-identical objects will be found
        return array[index].equals(item) && index < size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (E[]) new Object[16];
        size = 0;
    }
}
