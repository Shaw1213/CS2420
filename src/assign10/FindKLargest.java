package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker, Janne Wald, and Shawn Zang.
 * @version April 11, 2024.
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if(k < 0 || k > items.size()){
			throw new IllegalArgumentException("k must be non-negative and no larger than the size of the list");
		}

		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<E>(items);

		List<E> result = new ArrayList<E>();
		//extract the k largest items
		for(int i = 0; i < k; i++){
			result.add(maxHeap.extractMax());
		}

		return result;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k < 0 || k > items.size()){
			throw new IllegalArgumentException("k must be non-negative and no larger than the size of the list");
		}

		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<E>(items, cmp);

		List<E> result = new ArrayList<E>();
		//extract
		for(int i = 0; i < k; i++){
			result.add(maxHeap.extractMax());
		}

		//Collections.reverse(result);

		return result;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if(k < 0 || k > items.size()){
			throw new IllegalArgumentException("k must be non-negative and no larger than the size of the list");
		}

		Collections.sort(items);//Sort the list using nature order

		//return the last k element in descending order
		return new ArrayList<>(items.subList(items.size() - k, items.size()));
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k < 0 || k > items.size()){
			throw new IllegalArgumentException("k must be non-negative and no larger than the size of the list");
		}

		Collections.sort(items, cmp);//Sort the list using the comparator

		//return the last k element in descending order
		return new ArrayList<>(items.subList(items.size() - k, items.size()));
	}
}