package assign09;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a testing class for our custom HashTable
 *
 * @author Janne Wald and Shawn Zhang
 * @version April 4, 2024.
 */
public class HashTableTest {
    @Test
    void testPutAndGet() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        assertEquals(10, hashTable.get("key1"));
        assertEquals(20, hashTable.get("key2"));
        assertNull(hashTable.get("key3")); // Key does not exist
    }

    @Test
    void testRemove() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        assertEquals(10, hashTable.remove("key1"));
        assertNull(hashTable.get("key1")); // Key should be removed
        assertNull(hashTable.remove("key3")); // Key does not exist
    }

    @Test
    void testContainsKey() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        assertTrue(hashTable.containsKey("key1"));
        assertFalse(hashTable.containsKey("key2")); // Key does not exist
    }

    @Test
    void testContainsValue() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        assertTrue(hashTable.containsValue(10));
        assertFalse(hashTable.containsValue(20)); // Value does not exist
    }

    @Test
    void testSize() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        assertEquals(0, hashTable.size());
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        assertEquals(2, hashTable.size());
    }

    @Test
    void testClear() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertNull(hashTable.get("key1"));
        assertNull(hashTable.get("key2"));
    }

    @Test
    void testEntries() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        List<MapEntry<String, Integer>> entries = hashTable.entries();
        assertEquals(2, entries.size());
        assertTrue(entries.contains(new MapEntry<>("key1", 10)));
        assertTrue(entries.contains(new MapEntry<>("key2", 20)));
    }

    @Test
    void testPutSameKeyMultipleTimes() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        hashTable.put("key1", 20); // Overwrite the value for the same key
        assertEquals(20, hashTable.get("key1")); // Should return the updated value
        assertEquals(1, hashTable.size()); // Size should remain 1
    }

    @Test
    void testRemoveNonExistentKey() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        assertNull(hashTable.remove("key2")); // Removing a non-existent key should return null
        assertEquals(1, hashTable.size()); // Size should remain unchanged
    }

    @Test
    void testRehashing() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        // Add enough entries to trigger rehashing based on the load factor
        for (int i = 0; i < 20; i++) {
            hashTable.put("key" + i, i);
        }
        // Check that entries are still accessible after rehashing
        for (int i = 0; i < 20; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }
        assertTrue(hashTable.size() > 10); // The size should be greater than the initial capacity
    }

    @Test
    void testClearOnEmptyTable() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.clear(); // Clearing an empty table
        assertEquals(0, hashTable.size()); // Size should remain 0
    }

    @Test
    void testPutNullKey() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        assertThrows(NullPointerException.class, () -> hashTable.put(null, 10)); // Should throw NullPointerException
    }

    @Test
    void testPutNullValue() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", null); // Putting a null value is allowed
        assertNull(hashTable.get("key1")); // Should return null
    }

    @Test
    void testRemoveAllEntries() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        hashTable.remove("key1");
        hashTable.remove("key2");
        assertTrue(hashTable.isEmpty());
    }
}
