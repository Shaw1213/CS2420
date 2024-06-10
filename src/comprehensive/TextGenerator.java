package comprehensive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A text generator that uses a Markov Chain to generate text based on a given seed.
 * The seed is the starting word(s) for the text generation.
 * The Markov Chain is built from a list of words from a file.
 * The text generation can be done in three modes:
 * 1. Default: The text generation starts with the seed and continues with the most probable words.
 * 2. All: The text generation starts with the seed and continues with random words.
 * 3. One: The text generation starts with the seed and continues with the most probable words.
 * The text generation stops after k words.
 *
 * @version April 23, 2024
 * @author uthor: Shawn Zhang, Janne Wald
 */

public class TextGenerator {

    /**
     * Main method for the TextGenerator.
     * @param args The arguments for the TextGenerator.
     *             args[0] = filename
     *             args[1] = seed
     *             args[2] = k
     *             args[3] = mode (optional)
     *             If mode is not provided, the default mode is used.
     */
    public static void main(String[] args) {
        // If arguments not fully there give rundown of command usage
        if (args.length < 3) {
            System.out.println("Welcome to the Text Generator!");
            System.out.println("Usage: java TextGenerator <filename> <seed> <K> [all|one]");
            System.exit(1);
        }
        // Parse arguments
        String filename = args[0];
        String originalSeed = args[1].toLowerCase();
        int k = Integer.parseInt(args[2]);
        String mode = args.length > 3 ? args[3] : "default";

        // Read file
        ArrayList<String> words = readFile(filename);
        // Error if empty
        if (words.isEmpty()) {
            System.out.println("Error: File is empty or not found.");
            return;
        }

        MarkovChain mc = new MarkovChain(words);

        for (int run = 0; run < 1; run++) {  // Currently set to 1 for a single run, increase for multiple runs
            String seed = originalSeed;  // Reset seed to original at the start of each run
            StringBuilder output = new StringBuilder();  // Start with a fresh StringBuilder for each run

            // Generate the text based on the mode
            switch (mode) {
                case "all":
                    // Start with seed
                    output.append(seed);
                    // Get next random word
                    String tempRand = mc.getRandomNextWord(seed);
                    // Until we reach k
                    for (int i = 1; i < k; i++) {
                        // Add next random word unless it's a no-pair, reset starting at seed
                        if (tempRand.equals("."))
                            tempRand = seed;
                        output.append(" ").append(tempRand);
                        // Calculate next random word
                        tempRand = mc.getRandomNextWord(tempRand);
                    }
                    break;
                case "one":
                    // Start with seed
                    output.append(seed);
                    // Get next probable word
                    String tempProb = mc.getMostProbable(seed);
                    // Until we reach k
                    for (int i = 1; i < k; i++) {
                        // Add next probable word unless it's a no-pair, reset starting at seed
                        if (tempProb.equals("."))
                            tempProb = seed;
                        output.append(" ").append(tempProb);
                        tempProb = mc.getMostProbable(tempProb);
                    }
                    break;
                default:
                    // Do not start with seed
                    var mostProbable = mc.getOrdered(seed);
                    // Check if k is too big for list of possible words
                    int iterations = k;
                    if(iterations > mostProbable.length)
                        iterations = mostProbable.length;
                    // Add all words
                    for (int i = 0; i < iterations; i++) {
                        output.append(mostProbable[i]);
                        output.append(" ");
                    }
                    break;
            }
            System.out.println(output.toString());
        }
    }

    /**
     * Reads a file and returns a list of words.
     * @param filename The name of the file to read
     * @return A list of words from the file
     */
    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");  // Matches only valid word characters
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                // Efficiently find matches and add them to the list
                Matcher matcher = pattern.matcher(line.toLowerCase());
                while (matcher.find()) {
                    words.add(matcher.group());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }
}
