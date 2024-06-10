package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a class that makes a HashTables from scratch. Uses generic programming along with key/value pairs.
 * Will implement separate chaining.
 *
 * @param <K> Key for value being stored
 * @param <V> Value being stored
 *
 * @author Janne Wald and Shawn Zhang
 * @version April 4, 2024.
 */

public class HashTable<K, V> implements Map<K, V>{

    private ArrayList<LinkedList<MapEntry<K, V>>> table;

    private int size; // number of entries in the map

    /**
     * Constructs a new, empty hash table with the specified initial capacity.
     * <p>
     * O(1) for quadratic probing or separate chaining
     */
    public HashTable() {
        int capacity = 100;// default capacity
        table = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }

        size = 0;
    }

    /**
     * Constructs a new, empty hash table with double the specified initial capacity.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     */
    private void rehash() {
        int newCapacity = table.size() * 2; //double the cap
        ArrayList<LinkedList<MapEntry<K, V>>> newTable = new ArrayList<>();
        for (int i = 0; i < newCapacity; i++) {
            newTable.add(new LinkedList<>());
        }

        //rehash
        for (LinkedList<MapEntry<K, V>> chain : table) {
            for (MapEntry<K, V> entry : chain) {
                int newIndex = hash(entry.getKey(), newCapacity);
                newTable.get(newIndex).add(entry);
            }
        }

        table = newTable; //update the table
    }

    /**
     * Returns the hash value for the specified key.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key the key
     * @param capacity the capacity of the table
     * @return the hash value for the specified key
     */
    private int hash(K key, int capacity) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length) for quadratic probing or separate chaining
     */
    @Override
    public void clear() {
        for (LinkedList<MapEntry<K, V>> mapEntries : table) {
            mapEntries.clear();
        }
        size = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key the key
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int index = hash(key, table.size());
        LinkedList<MapEntry<K, V>> chain = table.get(index);

        for (MapEntry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     * <p>
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @param value the value
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        //for every chain in the table and for every entry in the chain.
        for (LinkedList<MapEntry<K, V>> chain : table) {
            for (MapEntry<K, V> entry : chain) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a List view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * <p>
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> entriesList = new ArrayList<>();

        for (LinkedList<MapEntry<K, V>> chain : table) {
            entriesList.addAll(chain);
        }

        return entriesList;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key the key
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        int index = hash(key, table.size());
        LinkedList<MapEntry<K, V>> chain = table.get(index);

        for (MapEntry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                return entry.getValue(); //found
            }
        }

        return null; //not found
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key the key
     * @param value the value
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        int index = hash(key, table.size());
        LinkedList<MapEntry<K, V>> chain = table.get(index);

        for (MapEntry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);//update the value
                return oldValue;//return the old value
            }
        }

        //if key found, add a new entry
        chain.add(new MapEntry<>(key, value));
        size++;

        //check if rehash is needed
        double loadFactor = (double) size / table.size();
        if (loadFactor > 1.0) { //as requirement is 10.0
            rehash();
        }

        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key the key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        int index = hash(key, table.size());
        LinkedList<MapEntry<K, V>> chain = table.get(index);

        Iterator<MapEntry<K, V>> iterator = chain.iterator();
        while (iterator.hasNext()) {
            MapEntry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                iterator.remove();//remove the entry
                size--;
                return oldValue;
            }
        }

        return null;// key not found;
    }

    /**
     * Determines the number of mappings in this map.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return size;
    }
}
