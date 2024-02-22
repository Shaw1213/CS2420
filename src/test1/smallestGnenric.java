package test1;

public class smallestGnenric {
    public static < T extends Comparable <T>> T findLargest(T [] array){
      if (array == null || array.length == 0){
           return null;
       }

      T largest = array[0];

      for (int i = 1; i < array.length; i++){
          if (array[i].compareTo(largest) > 0){
              largest = array[i];
          }
      }
      return largest;
    }
}
