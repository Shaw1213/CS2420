package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BSTTimer {

    public static void main(String[] args) {
        int step = 1000;
        int maxItems = 20000;
        Random random = new Random();

        for (int n = step; n <= maxItems; n += step) {
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                items.add(i); // Sorted order
            }

            // Measure time for adding items in sorted order and checking contains
            BinarySearchTree<Integer> sortedBST = new BinarySearchTree<>();
            long sortedAddTime = measureAddTime(sortedBST, items);
            long sortedContainsTime = measureContainsTime(sortedBST, items);
            sortedBST.removeAll(items);


            // Measure time for adding items in random order and checking contains
            Collections.shuffle(items, random); // Randomize the order
            BinarySearchTree<Integer> randomBST = new BinarySearchTree<>();
            long randomAddTime = measureAddTime(randomBST, items);
            long randomContainsTime = measureContainsTime(randomBST, items);
            randomBST.removeAll(items);

            System.out.println("N = " + n + ": Sorted Add Time = " + sortedAddTime + " ms, Sorted Contains Time = " + sortedContainsTime +
                    " ms, Random Add Time = " + randomAddTime + " ms, Random Contains Time = " + randomContainsTime + " ms");
        }
    }

    private static long measureAddTime(BinarySearchTree<Integer> bst, List<Integer> items) {
        long startTime = System.currentTimeMillis();
        for (Integer item : items) {
            bst.add(item);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static long measureContainsTime(BinarySearchTree<Integer> bst, List<Integer> items) {
        long startTime = System.currentTimeMillis();
        for (Integer item : items) {
            bst.contains(item);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
