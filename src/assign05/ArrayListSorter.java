package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {

    private static final int threshold = 10;
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list){
        //base case
        if (list.size() <= 1) {
            return;
        }

        ArrayList<T> aux = new ArrayList<>(Collections.nCopies(list.size(), null));
        mergesort(list, aux, 0, list.size() - 1);
    }

    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list, ArrayList<T> aux, int low, int high){
        // If the sublist is below the threshold, use insertion sort
        if (high - low <= threshold) {
            insertionSort(list, low, high);
            return;
        }
        int mid = low + (high - low) / 2;
        mergesort(list, aux, low, mid);
        mergesort(list, aux, mid + 1, high);
        merge(list, aux, low, mid, high);
    }

    //helper method

    private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> aux, int low, int mid, int high){
        if(high <= low) {
            return;
        }

        for(int i = low; i <= high; i++){
            aux.set(i, list.get(i));
        }

        int k = low, j = mid + 1;
        for(int i = low; i <= high; i++){
            if(k > mid){
                list.set(i, aux.get(j++));
            }else if(j > high){
                list.set(i, aux.get(k++));
            }else if(aux.get(j).compareTo(aux.get(k)) < 0){
                list.set(i, aux.get(j++));
            }else{
                list.set(i, aux.get(k++));
            }
        }
    }

    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list,int left, int right) {

        for (int i = left + 1; i <= right; i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= left && list.get(j).compareTo(key) > 0) {
                list.set(j + 1,list.get(j));
                j--;
            }
            list.set(j + 1 , key);
        }
    }

    private static final Random random = new Random();
    public enum PivotStrategy{
        first,
        middle,
        random
    }
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list){
        quicksort(list, 0, list.size() - 1, PivotStrategy.first);//change strategy
    }

    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list,int low,int high,PivotStrategy strategy){
        if (low < high) {
            int pivotIndex = partition(list, low, high, strategy);
            quicksort(list, low, pivotIndex - 1, strategy);
            quicksort(list, pivotIndex + 1, high, strategy);
        }
    }

    private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int low, int high, PivotStrategy strategy) {
        int pivotIndex = switch (strategy){
            case first -> low;
            case middle -> low + (high - low) / 2;
            case random -> low + random.nextInt(high -low + 1);
        };

        Collections.swap(list, pivotIndex, high);
        T pivot = list.get(high);

        int i = low - 1;
        for(int k = low; k < high; k++){
            if(list.get(k).compareTo(pivot) <= 0){
                i++;
                Collections.swap(list, i, k);
            }
        }
        Collections.swap(list, i +1 , high);
        return i+1;
    }


        public static ArrayList<Integer> generateAscending(int size){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            list.add(i);
        }

        return list;
    }

    public static ArrayList<Integer> generatePermuted(int size){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            list.add(i);
        }

        Collections.shuffle(list);

        return list;
    }

    public static ArrayList<Integer> generateDescending(int size){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = size; i >= 1; i--){
            list.add(i);
        }

        return list;
    }

}
