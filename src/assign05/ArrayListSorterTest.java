package assign05;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayListSorterTest {

    /**
     * merge sort test
     */
    //helper method test
//    @Test
//    void testInsertionSort(){
//        int size = 10;
//        ArrayList<Integer> list = ArrayListSorter.generatePermuted(size);
//        ArrayList<Integer> Answer = ArrayListSorter.generateAscending(size);
//
//        ArrayListSorter.insertionSort(list, 0, list.size() - 1);
//
//        assertEquals(Answer, list);
//    }
//
//    @Test
//    void testInsertionSortEdge(){
//        int size = 10000;
//        ArrayList<Integer> list = ArrayListSorter.generatePermuted(size);
//        ArrayList<Integer> Answer = ArrayListSorter.generateAscending(size);
//
//        ArrayListSorter.insertionSort(list, 0, list.size() - 1);
//
//        assertEquals(Answer, list);
//    }
//
//    @Test
//    public void testInsertionSortScope() {
//        ArrayList<Integer> list = ArrayListSorter.generatePermuted(20);
//
//        ArrayList<Integer> originalList = new ArrayList<>(list);
//
//        int left = 5;
//        int right = 14;
//
//        ArrayListSorter.insertionSort(list, left, right);
//
//        for (int i = left + 1; i <= right; i++) {
//            assertTrue(list.get(i - 1).compareTo(list.get(i)) <= 0);
//        }
//
//        for (int i = 0; i < left; i++) {
//            assertEquals(originalList.get(i), list.get(i));
//        }
//        for (int i = right + 1; i < list.size(); i++) {
//            assertEquals(originalList.get(i), list.get(i));
//        }
//    }

//    @Test
//    public void testMerge() {
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 2, 4, 6, 8));
//        ArrayList<Integer> aux = new ArrayList<>(Collections.nCopies(list.size(), 0));
//        ArrayListSorter.merge(list, aux, 0, 3, 7);
//        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), list);
//    }
//    @Test
//    public void testMergeWithDuplicates() {
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 1, 3, 5, 7));
//        ArrayList<Integer> aux = new ArrayList<>(Collections.nCopies(list.size(), 0));
//        ArrayListSorter.merge(list, aux, 0, 3, 7);
//        assertEquals(Arrays.asList(1, 1, 3, 3, 5, 5, 7, 7), list);
//    }
//
//    @Test
//    public void testMergeWithEmptyList() {
//        ArrayList<Integer> list = new ArrayList<>();
//        ArrayList<Integer> aux = new ArrayList<>();
//        ArrayListSorter.merge(list, aux, 0, 0, 0);
//        assertEquals(new ArrayList<Integer>(), list);
//    }

    @Test
    public void testMergeSort(){
        int size = 10;
        ArrayList<Integer> listA = ArrayListSorter.generateAscending(size);
        ArrayList<Integer> listB = ArrayListSorter.generatePermuted(size);
        ArrayListSorter.mergesort(listB);
        assertEquals(listA, listB);
    }

    @Test
    public void testMergeSortEdge(){
        int size = 10000000;
        ArrayList<Integer> listA = ArrayListSorter.generateAscending(size);
        ArrayList<Integer> listB = ArrayListSorter.generatePermuted(size);
        ArrayListSorter.mergesort(listB);
        assertEquals(listA, listB);
    }

    /**
     * quick sort test
     */
//helper test
//    @Test
//    public void testPartitionFirst(){
//        ArrayList<Integer> list = ArrayListSorter.generatePermuted(10);
//        int pivotIndex = ArrayListSorter.partition(list,0,list.size() - 1, ArrayListSorter.PivotStrategy.first);
//        for(int i = 0; i < pivotIndex; i++){
//            assertTrue(list.get(i).compareTo(list.get(pivotIndex)) <= 0);
//        }
//        for (int i = pivotIndex + 1; i < list.size(); i++) {
//            assertTrue(list.get(i).compareTo(list.get(pivotIndex)) >= 0);
//        }
//    }
//
//    @Test
//    public void testPartitionMiddle(){
//        ArrayList<Integer> list = ArrayListSorter.generatePermuted(10);
//        int pivotIndex = ArrayListSorter.partition(list,0,list.size() - 1, ArrayListSorter.PivotStrategy.middle);
//        for(int i = 0; i < pivotIndex; i++){
//            assertTrue(list.get(i).compareTo(list.get(pivotIndex)) <= 0);
//        }
//        for (int i = pivotIndex + 1; i < list.size(); i++) {
//            assertTrue(list.get(i).compareTo(list.get(pivotIndex)) >= 0);
//        }
//    }
//
//    @Test
//    public void testPartitionRandom(){
//        ArrayList<Integer> list = ArrayListSorter.generatePermuted(10);
//        int pivotIndex = ArrayListSorter.partition(list,0,list.size() - 1, ArrayListSorter.PivotStrategy.random);
//        for(int i = 0; i < pivotIndex; i++){
//            assertTrue(list.get(i).compareTo(list.get(pivotIndex)) <= 0);
//        }
//        for (int i = pivotIndex + 1; i < list.size(); i++) {
//            assertTrue(list.get(i).compareTo(list.get(pivotIndex)) >= 0);
//        }
//    }

    @Test
    public void testQuickSort(){
        int size = 10;
        ArrayList<Integer> listA = ArrayListSorter.generateAscending(size);
        ArrayList<Integer> listB = ArrayListSorter.generatePermuted(size);
        ArrayListSorter.quicksort(listB);
        assertEquals(listA, listB);
    }

    @Test
    public void testQuickSortEdge(){
        int size = 999999;
        ArrayList<Integer> listA = ArrayListSorter.generateAscending(size);
        ArrayList<Integer> listB = ArrayListSorter.generatePermuted(size);
        ArrayListSorter.quicksort(listB);
        assertEquals(listA, listB);
    }

    /**
     * generate test
     */
    @Test
    void generateAscending() {
        int size = 10;
        ArrayList<Integer> result = ArrayListSorter.generateAscending(size);
        assertEquals(size,result.size());//size check
        //check order
        for(int i = 0; i < size; i++){
            assertEquals(i + 1,result.get(i));
        }
    }

    @Test
    void generatePermuted() {
        int size = 10;
        ArrayList<Integer> result = ArrayListSorter.generatePermuted(size);
        assertEquals(size,result.size());//size check
        //check if unsorted list contains i
        for(int i = 1; i <= size; i++){
            assertTrue(result.contains(i));
        }
    }

    @Test
    void generateDescending() {
        int size = 10;
        ArrayList<Integer> result = ArrayListSorter.generateDescending(size);
        assertEquals(size,result.size());//size check
        for(int i = size; i >= 1; i--){
            assertEquals(i,result.get(size-i));
        }

    }
}

