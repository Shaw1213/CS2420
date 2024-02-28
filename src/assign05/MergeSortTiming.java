package assign05;

import java.util.ArrayList;

public class MergeSortTiming {
    public static void main(String[] args) {

        for(int i = 0; i < 100; i++) {
            int listSize = 1000000; // Example list size

            ArrayList<Integer> list = ArrayListSorter.generateDescending(listSize);

            long startTime = System.nanoTime();

            ArrayListSorter.mergesort(list);

            long endTime = System.nanoTime();

            long duration = endTime - startTime;

            double durationInMilliseconds = duration / 1_000_000.0;

            System.out.println("Sorting generateDescending with mergesort " + listSize + " elements took " + duration + " nanoseconds (" + durationInMilliseconds + " milliseconds).");
        }
    }
}


