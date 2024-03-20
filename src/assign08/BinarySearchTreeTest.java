package assign08;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(List.of(5, 3, 7, 2, 4, 6, 8));
        bst.addAll(list);

        System.out.println(bst.getRoot().generateDot());

        // Test Clear(passed)
//        bst.clear();
//
//        boolean isEmpty = false;
//        if (bst.getRoot() == null) {
//            isEmpty = true;
//        }
//        System.out.println(isEmpty);

//        // Test contains(passed)
//        boolean contains = bst.contains(5);
//        boolean contains1 = bst.contains(1);
//        System.out.println(contains1);
//        System.out.println(contains);

//        //Test containsAll(passed)
//        boolean containsAll = bst.containsAll(list);
//        System.out.println(containsAll);
//        ArrayList<Integer> list1 = new ArrayList<>();
//        list1.addAll(List.of(5, 3, 7, 2, 4, 6, 8, 9,11));
//        boolean containsAll1 = bst.containsAll(list1);
//        System.out.println(containsAll1);

//        //Test first last(passed)
//        System.out.println(bst.first());
//
//        System.out.println(bst.last());

//        //Test isEmpty(passed)
//        bst.clear();
//        System.out.println(bst.isEmpty());







    }
}
