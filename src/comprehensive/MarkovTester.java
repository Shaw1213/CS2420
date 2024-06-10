package comprehensive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MarkovTester {

    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> words = new ArrayList<>();
        try (Scanner fileReader = new Scanner(new File(filename))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                // Match any non-alphanumeric character
                var pattern = Pattern.compile("[^a-zA-Z0-9]");
                String[] spacedWords = line.toLowerCase().split("\\s+");
                for (String word : spacedWords) {
                    // Avoid adding empty strings
                    if (!word.isEmpty()) {
                        String[] parts = pattern.split(word);
                        // Parts will be 0 size if "words" contain only odd characters, do not add.
                        if(parts.length != 0){
                            String half = parts[0];
                            words.add(half);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return words;
        }
        return words;
    }

    public static void main(String[] args){
        var chain = new MarkovChain(readFile("Dragons.txt"));
        var start = System.currentTimeMillis();
        var moby = new MarkovChain(readFile("MobyDick.txt"));
        //System.out.println(moby.getMostProbable("moby"));
        //System.out.println(moby.getMostProbable("seamen"));
        var the = moby.getOrdered("the");
        System.out.println(the.length);
        System.out.println(Arrays.toString(the));
        System.out.println("Time to look through all of moby dick to find words that are after THE was: " + (System.currentTimeMillis() - start) + " ms");
        // System.out.println(Arrays.toString(moby.getOrdered("abbacus")));
        // System.out.println(Arrays.toString(moby.getOrdered("randomword")));

    }
}
