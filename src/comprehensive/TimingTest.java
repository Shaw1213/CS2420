package comprehensive;

import assign10.BinaryMaxHeap;

import java.util.ArrayList;
import java.util.Random;
import static comprehensive.TextGenerator.readFile;

public class TimingTest {

    public static String randomWord(Random rng) {
        String word = null;
        switch (rng.nextInt(5)) {
            case 0:
                word = "Hey";
                break;
            case 1:
                word = "Im";
                break;
            case 2:
                word = "a";
                break;
            case 3:
                word = "small";
                break;
            case 4:
                word = "word";
                break;
        }
        return word;
    }

    public static void main(String[] args) {
        var maxElements = 100000;
        var timeToLoop = 500;
        int elementCount;
        int i;
        StringBuilder matlabN;
        StringBuilder matlabAVGtime;
        ArrayList<String> words;
        MarkovChain mc = null;
        Random rng;


        // =========================== Probable Test ===========================
        System.out.println("\n\n**Testing Getting Probable Words**");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                words = new ArrayList<String>();
                rng = new Random(3);
                for (int j = 0; j < elementCount; j++) {
                    var word = randomWord(rng);
                    if(word != null)
                        words.add(word);
                }
                mc = new MarkovChain(words);
                var start = System.nanoTime();
                mc.getMostProbable(randomWord(rng));
                var end = System.nanoTime();
                sum += end - start;
            }

            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get most probable on " + elementCount + " , sized list of random words: " + avg + " ns");

        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== Random Test ===========================
        System.out.println("\n\n**Testing Getting Random Words**");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                words = new ArrayList<String>();
                rng = new Random(3);
                for (int j = 0; j < elementCount; j++) {
                    var word = randomWord(rng);
                    if(word != null)
                        words.add(word);
                }
                mc = new MarkovChain(words);
                var start = System.nanoTime();
                mc.getRandomNextWord(randomWord(rng));
                var end = System.nanoTime();
                sum += end - start;
            }

            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get random markov path on " + elementCount + " , sized list of random words: " + avg + " ns");

        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== K Test ===========================
        System.out.println("\n\n**Testing Getting Random Words**");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");
        timeToLoop = 200;

        for (int k = 0; k <= 2000; k += 200) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                words = readFile("MobyDick.txt");
                mc = new MarkovChain(words);
                var start = System.nanoTime();
                var unformatted = mc.getOrdered("the");
                var formatted = new String[k];
                for(int h = 0; h < k; h++)
                    formatted[h] = unformatted[h];
                var end = System.nanoTime();
                sum += end - start;
            }

            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(k + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get k sized list of ordered words at " + k + " , size K on Moby Dick: " + avg + " ns");

        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);


    }
}