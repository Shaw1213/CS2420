package assign08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class BSTPerformanceExperiment {

    private static final int MAX_ITEMS = 200000;
    private static final int STEP = 10000;

    public static void main(String[] args) {
        List<Long> treeSetAddTimes = new ArrayList<>();
        List<Long> treeSetContainsTimes = new ArrayList<>();
        List<Long> bstAddTimes = new ArrayList<>();
        List<Long> bstContainsTimes = new ArrayList<>();

        Random random = new Random();

        for (int n = STEP; n <= MAX_ITEMS; n += STEP) {
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                items.add(random.nextInt());
            }

            // Measure time for TreeSet
            TreeSet<Integer> treeSet = new TreeSet<>();
            long startTime = System.currentTimeMillis();
            items.forEach(treeSet::add);
            long endTime = System.currentTimeMillis();
            treeSetAddTimes.add(endTime - startTime);

            startTime = System.currentTimeMillis();
            items.forEach(treeSet::contains);
            endTime = System.currentTimeMillis();
            treeSetContainsTimes.add(endTime - startTime);

            // Measure time for BinarySearchTree
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            startTime = System.currentTimeMillis();
            items.forEach(bst::add);
            endTime = System.currentTimeMillis();
            bstAddTimes.add(endTime - startTime);

            startTime = System.currentTimeMillis();
            items.forEach(bst::contains);
            endTime = System.currentTimeMillis();
            bstContainsTimes.add(endTime - startTime);

            System.out.println("N = " + n + ": TreeSet Add Time = " + treeSetAddTimes.get(treeSetAddTimes.size() - 1) +
                    " ms, TreeSet Contains Time = " + treeSetContainsTimes.get(treeSetContainsTimes.size() - 1) +
                    " ms, BST Add Time = " + bstAddTimes.get(bstAddTimes.size() - 1) +
                    " ms, BST Contains Time = " + bstContainsTimes.get(bstContainsTimes.size() - 1) + " ms");
        }

        // Plotting code goes here (e.g., using a plotting library)
    }
}
