package comprehensive;

import java.util.*;

/**
 * A class that creates a Markov Chain from a list of words.
 * The Markov Chain is used to generate text based on the given seed.
 *
 * @version April 23, 2024
 * @author Shawn Zhang, Janne Wald
 */
public class MarkovChain {
    // Member variables
    private HashMap<String, FrequencyTable> pairs;
    private Random rand = new Random();

    /**
     * A class that creates a frequency table for a given word.
     */
    public class FrequencyTable {
        // Member variables
        private String probable;
        private HashMap<String, Integer> frequencies;
        private TreeMap<String, Integer> ordered;
        private int totalFrequencies;

        /**
         * This is a custom Frequency Table class that stores the current highest frequency and collects all others
         *
         * @param word The first word to be added in the frequency table with a value of 1 occurrence.
         */
        public FrequencyTable(String word) {
            frequencies = new HashMap<>();
            // Add frequency of 1
            frequencies.put(word, 1);
            // Compares words with the highest frequencies, tie-braking lexicographically
            Comparator<String> cmp = new Comparator<String>() {
                public int compare(String key1, String key2) {
                    int result = frequencies.get(key2) - frequencies.get(key1);
                    if (result == 0)
                        result = key1.compareTo(key2);
                    return result;
                }
            };
            ordered = new TreeMap<>(cmp);
            probable = word;
            totalFrequencies = 1;
        }


        /**
         * Compares a recently added word to current most frequent word, will result in O(1) lookup time for getMostProbable
         *
         * @param word latest word added
         */
        private void compareHighest(String word) {
            // If we dont have a current probable
            if (probable == null) {
                probable = word;
                return;
            }
            // Get frequencies
            int currentFrequency = frequencies.get(word);
            int probableFrequency = frequencies.get(probable);

            // Check if the new word has a higher frequency
            if (currentFrequency > probableFrequency) {
                probable = word;
            }
            // If frequencies are the same, choose the lexicographically smaller word
            else if (currentFrequency == probableFrequency && word.compareTo(probable) < 0) {
                probable = word;
            }
            // Do nothing if not bigger
        }

        /**
         * Adds a word to the frequency table.
         *
         * @param word The word to be added.
         */
        public void add(String word) {
            // If word already indexed, add 1 to currents frequency
            if (frequencies.containsKey(word))
                frequencies.put(word, frequencies.get(word) + 1);
                // If word not indexed add to map, set 1 as its frequency
            else
                frequencies.put(word, 1);
            // Compare
            compareHighest(word);
            totalFrequencies++;
        }

        /**
         * Returns the most probable word.
         *
         * @return The most probable word.
         */
        public String getProbable() {
            return probable;
        }

/**
         * Returns an array of words ordered by frequency.
         *
         * @return An array of words ordered by frequency.
         */
        public String[] getOrdered() {
            // Inserts all known frequencies into ordered treemap.
            // Inserting all known frequencies at the end should be faster than constantly percolating during collection.
            ordered.putAll(frequencies);
            return ordered.keySet().toArray(new String[0]);
        }

        /**
         * Returns a random word based on the frequency of the words.
         *
         * @return A random word based on the frequency of the words.
         */
        public String randomPair() {
            int index = rand.nextInt(totalFrequencies); // Use the shared Random instance
            int sum = 0;
            for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
                sum += entry.getValue();
                if (index < sum) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

    /**
     * Constructs a Markov Chain from a list of words.
     *
     * @param words The list of words to be used.
     */
    public MarkovChain(ArrayList<String> words) {
        // Note: "." means no pair for word
        // Edge Cases
        if (words.size() == 0)
            throw new IllegalArgumentException("Must contain words");
        if (words.size() == 1) {
            pairs = new HashMap<>();
            pairs.put(words.get(0), new FrequencyTable("."));
        } else {
            // Add every pair between first and second to last
            pairs = new HashMap<>();

            for (int i = 0; i < words.size() - 1; i++) {
                var current = words.get(i);
                var next = words.get(i + 1);
                if (pairs.containsKey(current))
                    pairs.get(current).add(next);
                else
                    pairs.put(current, new FrequencyTable(next));

            }
            // Add last
            var last = words.get(words.size() - 1);
            if (pairs.containsKey(last))
                pairs.get(last).add(".");
            else
                pairs.put(last, new FrequencyTable("."));
        }
    }

    /**
     * Returns the most probable word based on the given word.
     * @param word The word to be used.
     * @return The most probable word.
     */
    public String getMostProbable(String word) {
        return pairs.get(word).getProbable();
    }

    /**
     * Returns an array of words ordered by frequency.
     * @param word The word to be used.
     * @return An array of words ordered by frequency.
     */
    public String[] getOrdered(String word) {
        return pairs.get(word).getOrdered();
    }

    /**
     * Returns a random word based on the frequency of the words.
     * @param word The word to be used.
     * @return A random word based on the frequency of the words.
     */
    public String getRandomNextWord(String word) {
        return pairs.get(word).randomPair();
    }
}
