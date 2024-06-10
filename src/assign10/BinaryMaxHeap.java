package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class creates a generic Binary Max Heap.
 *
 * @param <E> The object being stored in the heap, must implement comparable or have comparator.
 * @author Janne Wald and Shawn Zang.
 * @version April 11, 2024.
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
    // Comparator state, if null, we use Comparable, if a comparator then...Comparator
    private Comparator<? super E> cmp;
    private E[] tree;
    private int size;
    private final int startSize = 10;

    /**
     * This creates an empty binary heap, it is assumed that the elements are ordered using their natural ordering
     */
    public BinaryMaxHeap() {
        cmp = null;
        tree = (E[]) new Object[startSize];
        size = 0;
    }

    /**
     * This creates an empty binary heap, it is assumed that the elements are ordered using the provided Comparator object.
     *
     * @param cmp A comparator object for the provided type.
     */
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        this.cmp = cmp;
        tree = (E[]) new Object[startSize];
        size = 0;
    }

    /**
     * A binary heap is constructed from the given list.
     * Also, it is assumed that the elements are ordered using their natural ordering.
     *
     * @param list A list of elements to be added
     */
    public BinaryMaxHeap(List<? extends E> list) {
        cmp = null;
        size = list.size();
        // Tree is built in buildHeap
        buildHeap(list);
    }

    /**
     * Constructs a binary heap is constructed from the given list (see RECALL note above).
     * Also, it is assumed that the elements are ordered using the provided Comparator object.
     *
     * @param list A list of elements to be added
     * @param cmp  A comparator object for the provided type.
     */
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.cmp = cmp;
        // Tree is built in buildHeap
        size = list.size();
        buildHeap(list);
    }

    /**
     * Swaps two cells in an array.
     *
     * @param i first index of the array
     * @param j second index of the array
     */
    private void swap(int i, int j) {
        var temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    /**
     * Calculates left child index in an array based binary tree
     *
     * @param i index of current node in tree
     * @return the left child index position
     */
    private int leftChild(int i) {
        return i * 2;
    }

    /**
     * Calculates right child index in an array based binary tree
     *
     * @param i index of current node in tree
     * @return the right child index position
     */
    private int rightChild(int i) {
        return i * 2 + 1;
    }

    /**
     * Calculates parent index in an array based binary tree
     *
     * @param i index of current node in tree
     * @return the parent index position
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Compares two objects in the heap
     *
     * @param a first object to compared to
     * @param b second objects that compared with
     * @return the comparison value, like compareTo's
     */
    private int compare(E a, E b) {
        if (cmp != null) return cmp.compare(a, b);
        else {
            return ((Comparable<E>) a).compareTo(b);
        }
    }

    /**
     * Starts at a node and continues up the tree swapping until it finds it proper placement.
     *
     * @param i the current node index in the tree
     */
    private void percolateUp(int i) {
        // If empty/just head, should not happen
        if (i <= 1) return;
        // Compare to parent, swap if greater
        var parent = parent(i);
        if (compare(tree[i], tree[parent]) > 0) {
            swap(i, parent);
            percolateUp(parent);
        }

    }

    /**
     * Starts at subtree root of i then progresses downwards, swapping nodes when needed.
     *
     * @param i The root of the subtree.
     */
    private void percolateDown(int i) {
        var max = i;
        var l = leftChild(i);
        var r = rightChild(i);
        // Find the index of the maximum element among the current node, its left child, and its right child
        if (l <= size && compare(tree[l], tree[max]) > 0) {
            max = l;
        }
        if (r <= size && compare(tree[r], tree[max]) > 0) {
            max = r;
        }
        // If the maximum element is not the current node, swap them and continue percolating down
        if (max != i) {
            swap(i, max);
            percolateDown(max);
        }
    }

    /**
     * Constructs a heap from a list efficiently without adding one by another.
     *
     * @param list The list of items being added to heap
     */
    private void buildHeap(List<? extends E> list) {
        // Build backing array
        tree = (E[]) new Object[list.size() + 1];
        size = list.size();

        // Copy to backing array
        for (int i = 0; i < list.size(); i++)
            tree[i + 1] = list.get(i);

        // Start from the last non-leaf node and percolate down
        for (int i = size / 2; i >= 1; i--) {
            percolateDown(i);
        }
    }

    /**
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item
     */
    @Override
    public void add(E item) {
        // If tree is empty
        if (size == 0) {
            size++;
            tree[1] = item;
        }
        // If we are going over capacity, double array
        else if (size >= tree.length - 1) {
            var temp = (E[]) new Object[tree.length * 2];
            for (int i = 1; i <= size; i++) //Copy element up to size
                temp[i] = tree[i];
            tree = temp;
            size++;
            tree[size] = item;
            percolateUp(size);
        }
        // Adds to the end of heap and percolates up
        else {
            tree[size + 1] = item;
            size++;
            percolateUp(size);
        }
    }

    /**
     * Returns, but does not remove, the maximum item this priority queue.
     * O(1)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException("This heap is empty");
        return tree[1];
    }

    /**
     * Returns and removes the maximum item this priority queue.
     * O(log N)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        // If empty
        if (size == 0) throw new NoSuchElementException("There are no elements in this heap");
        // Get max
        var max = tree[1];
        // If just root
        if (size == 1) {
            tree[1] = null;
            size--;
            return max;
        }
        // Put last in root
        tree[1] = tree[size];
        tree[size] = null;
        size--;
        percolateDown(1);
        return max;
    }

    /**
     * Returns the number of items in this priority queue.
     * O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this priority queue is empty, false otherwise.
     * O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    @Override
    public void clear() {
        tree = (E[]) new Object[10];
        size = 0;
    }

    /**
     * Creates and returns an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public E[] toArray() {
        if (size == 0) return (E[]) new Object[0];

        var temp = (E[]) new Object[size];
        // Shift entire array down one
        for (int i = 0; i < size; i++)
            temp[i] = tree[i + 1];
        return temp;
    }


}
