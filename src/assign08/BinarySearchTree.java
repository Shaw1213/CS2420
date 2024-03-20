package assign08;

import java.util.*;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>{
    //Instance variables
    private BinaryNode<Type> root;

    //Getter for DOT representation
    public BinaryNode<Type> getRoot() {
        return root;
    }

    //Constructor
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Ensures that this set contains the specified item.
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if (root == null){
            root = new BinaryNode<>(item);
            return true;
        } else {
            return addHelper(root, item);
        }
    }
    /**
     * Helper recursive method to add an item to the tree.
     * @param node - the root of the tree
     * @param item - the item to be added
     * @return true if the item was added, false if it was already in the tree
     */
    private boolean addHelper (BinaryNode<Type> node, Type item){
        int compareResult = item.compareTo(node.getData());
        if (compareResult == 0){
            //Item all ready here,exit
            return false;
        } else if (compareResult < 0){
            //Item is less than node.getData()
            if(node.getLeft() == null){
                node.setLeft(new BinaryNode<>(item));//Add item
                return false;
            } else {
                return addHelper(node.getLeft(), item);
            }
        } else {
            //Item is greater than node.getData()
            if(node.getRight() == null){
                node.setRight(new BinaryNode<>(item));//Add item
                return true;
            } else {
                return addHelper(node.getRight(), item);
            }
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean changed = false;
        for (Type item : items){
            if (add(item)){
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        BinaryNode<Type> curNode = root;
        //Iterate down the tree
        while(curNode != null){
            int compareResult = item.compareTo(curNode.getData());
            if (compareResult == 0){
                return true;
            } else if (compareResult < 0){
                curNode = curNode.getLeft();
            } else {
                curNode = curNode.getRight();
            }
        }
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type item : items){
            if (!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if(root == null){
            throw new NoSuchElementException("the set is empty");
        }
        BinaryNode<Type> curNode = root;
        while(curNode.getLeft() != null){
            curNode = curNode.getLeft();
        }
        return curNode.getData();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
    return root == null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (root == null){
            throw new NoSuchElementException("the set is empty");
        }
        BinaryNode<Type> curNode = root;
        while(curNode.getRight() != null){
            curNode = curNode.getRight();
        }
        return curNode.getData();
    }

    @Override
    public boolean remove(Type item) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        return false;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        if(root == null){
            return 0;
        }
        int size = 0;
        Queue<BinaryNode<Type>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            BinaryNode<Type> curNode = queue.remove();
            size++;
            if (curNode.getLeft() != null){
                queue.add(curNode.getLeft());
            }
            if (curNode.getRight() != null){
                queue.add(curNode.getRight());
            }
        }
        return size;
    }

    @Override
    public ArrayList<Type> toArrayList() {
        return null;
    }
}
