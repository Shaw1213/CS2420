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

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        int initialSize = size();//Get the initial size
        root = removeHelper(root, item);//Remove the item
        int newSize = size();//Get the new size
        return initialSize != newSize;//Return true if the size changed
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     *
     * @param node - the root of the tree
     * @param item - the item to be removed
     * @return the root of the tree after the item is removed
     */
    private BinaryNode<Type> removeHelper(BinaryNode<Type> node, Type item){
        if(node == null){
            return null;
        }
        int compareResult = item.compareTo(node.getData());
        if(compareResult < 0){//
            node.setLeft(removeHelper(node.getLeft(), item));//Remove from left
        } else if (compareResult > 0){
            node.setRight(removeHelper(node.getRight(), item));//Remove from right
        } else{
            //Node with two children
            if(node.getLeft() != null && node.getRight() != null){
                //Find the predecessor, the max in left tree
                BinaryNode<Type> predecessor = findMax(node.getLeft());
                //replace the node's data with predecessor
                node.setData(predecessor.getData());
                //remove the predecessor from the left tree
                node.setLeft(removeHelper(node.getLeft(), predecessor.getData()));
            } else {
                //Node with one or no child
                node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
            }
        }
        return node;
    }

    /**
     * Helper method to find the max in the left tree
     *
     * @param node - the root of the left tree
     * @return the max in the left tree
     */
    private BinaryNode<Type> findMax(BinaryNode<Type> node){
        if(node == null){
            return null;
        }
        while(node.getRight() != null){
            node = node.getRight();
        }
        return node;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean setChanged = false;
        for(Type item : items){
            if(remove(item)){
                setChanged = true;
            }
        }
        return setChanged;
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

    /**
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    /**
     * Helper method to traverse the tree in order
     * @param node - the root of the tree
     * @param list - the list to store the items
     * @return the list of items in the tree
     */
    private void inOrderTraversal(BinaryNode<Type> node, ArrayList<Type> list){
        if(node != null){
            inOrderTraversal(node.getLeft(), list);
            list.add(node.getData());
            inOrderTraversal(node.getRight(), list);
        }
    }
}
