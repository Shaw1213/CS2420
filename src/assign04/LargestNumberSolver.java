package assign04;

import java.math.BigInteger;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class contains methods for solving problems related to finding the largest number.
 *
 * @version February 5, 2023
 * @author Shawn Zhang and Joshua White
 */

public class LargestNumberSolver {


    /**
     * Sorts an array of objects according to the given comparator
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp){
        for(int i = 1; i < arr.length; i++){
            T insert = arr[i];
            int check = i-1;
            while (check >= 0 && cmp.compare(arr[check], insert) > 0){
                arr[check+1] = arr[check];
                check--;
            }
            arr[check+1] = insert;
        }
    }

    /**
     * A comparator that compares two integers by concatenating them and comparing the resulting strings.
     */
    private static final Comparator<Integer> cmp = new Comparator<Integer>(){
        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = "" + o1 + o2;
            String s2 = "" + o2 + o1;
            return Integer.parseInt(s2) - Integer.parseInt(s1);
        }
    };

    /**
     * This method takes an array of integers and returns the largest number.
     * The number is returned as a string.
     *
     * @param arr the array of integers
     * @return the largest number that can be formed.
     */
    protected static String findLargestString(Integer[] arr){
        var sorted = arr.clone();
        insertionSort(sorted,cmp);
        var builder = new StringBuilder();
        for(Integer i : sorted){
            builder.append(i);
        }
        return builder.toString();
    }

    /**
     * This method takes an array of integers and returns the largest number.
     * The number is returned as a BigInteger.
     *
     * @param arr the array of integers
     * @return the largest number that can be formed
     */
    public static BigInteger findLargestNumber(Integer[] arr){
        return new BigInteger(findLargestString(arr));
    }

    /**
     * This method takes an array of integers and returns the largest number that can be formed.
     * The number is returned as an int.
     *
     * @param arr the array of integers
     * @return the largest number that can be formed by concatenating the integers in the array
     * @throws OutOfRangeException if the number is too large to be represented as an int
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
        try{
            return Integer.parseInt(findLargestString(arr));
        }catch (NumberFormatException e){
            throw new OutOfRangeException("int");
        }
    }

    /**
     * This method takes an array of integers and returns the largest number that can formed.
     * The number is returned as a long.
     *
     * @param arr the array of integers
     * @return the largest number that can be formed by concatenating the integers in the array
     * @throws OutOfRangeException if the number is too large to be represented as a long
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
        try{
            return Long.parseLong(findLargestString(arr));
        }catch (NumberFormatException e){
            throw new OutOfRangeException("long");
        }
    }

    /**
     * This method takes a list of integer arrays and returns the sum of the largest
     * numbers in each array.  The sum is returned as a BigInteger.
     *
     * @param list the list of integer arrays
     * @return the sum of the largest numbers in each array
     */
    public static BigInteger sum(List<Integer[]> list){
        BigInteger totalSum = BigInteger.ZERO;

        for (int i = 0; i < list.size(); i++){
            Integer[] arr = list.get(i);
            BigInteger arraySum = findLargestNumber(arr);
            totalSum = totalSum.add(arraySum);
        }

        return totalSum;
    }

    /**
     * This method takes a list of integer arrays and returns the kth largest number
     * The kth larges number is returned as an array of integers.
     *
     * @param list the list of integer arrays
     * @param k the kth largest number to find
     * @return the kth largest number that can be formed.
     * @throws IllegalArgumentException if k is less than 1 or greater than the number of arrays
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
        Integer[] sums = new Integer[list.size()];
        for(int i = 0; i < list.size(); i++){
            sums[i] = findLargestInt(list.get(i));
        }
        var sorted = sums.clone();
        insertionSort(sorted,Integer::compareTo);
        Integer kthSum = sorted[sorted.length-1-k];
        for(int i = 0; i < list.size(); i++){
            if(Objects.equals(sums[i], kthSum)) return list.get(i);
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method generates list of integer arrays from an input file,
     *
     * @param filename the file name and the path.
     * @return a list of integer arrays which contains the content in the file
     * returns an empty list if the file does not exist
     */
    public static List<Integer[]> readFile(String filename){
        List<Integer[]> resultList = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(filename))){
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                Integer[] intArray = new Integer[tokens.length];

                for (int i = 0; i < tokens.length; i++){
                    intArray[i] = Integer.parseInt(tokens[i]);//turns string to int and store them
                }

                resultList.add(intArray);
            }
        } catch (IOException e){
                return new ArrayList<>();
        }

        return resultList;
    }

}
