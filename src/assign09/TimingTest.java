package assign09;

import java.util.HashMap;
import java.util.Random;

/**
 * This is a class designated for timing different hashcode and their efficiency in the custom HashTable class.
 *
 * @author Janne Wald and Shawn Zhang
 * @version April 3, 2024.
 */
public class TimingTest {
    /**
     * @return a random name of random length and characters.
     */
    public static String generateRandomName() {
        Random r = new Random();

        StringBuilder name = new StringBuilder();
        // Random length
        var nameLength = r.nextInt(4, 10);
        for (int i = 0; i < nameLength; i++)
            // Random letter
            name.append((char) (r.nextInt(26) + 'a'));
        return name.toString();
    }

    /**
     * @return A new 8 digit Uid, a lack of a digits represents 0s in the number
     */
    public static int generateRandomUID() {
        Random r = new Random();
        // Random 8 digit number
        return r.nextInt(9999999);
    }

    /**
     * Runs the actual timing experiments
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        var maxElements = 100000;
        var timeToLoop = 200;
        int elementCount;
        int i;
        StringBuilder matlabN;
        StringBuilder matlabAVGtime;
        StringBuilder matlabAVGcoll;
        HashTable<Integer, StudentBadHash> bad = null;
        HashTable<Integer, StudentMediumHash> medium = null;
        HashTable<Integer, StudentGoodHash> good = null;
        HashTable<Integer, Integer> justInts;

        // =========================== Bad Hashes Test ===========================
        System.out.println("\n\nTesting Adding Bad Hashes");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");
        matlabAVGcoll = new StringBuilder();
        matlabAVGcoll.append("Matlab AVG collision arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            var collisionCount = 0;
            for (i = 0; i < timeToLoop; i++) {
                bad = new HashTable<>();
                for (int j = 0; j < elementCount; j++) {
                    var student = new StudentBadHash(generateRandomUID(), generateRandomName(), "none");
                    var key = student.hashCode();
                    // Start
                    var start = System.nanoTime();
                    if (bad.put(key, student) != null) // Check for collision in basic arr
                        collisionCount++;
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            var collisionAvg = collisionCount / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            matlabAVGcoll.append(collisionAvg + " ");
            // Display current iteration to command line
            System.out.println("The time to insert an " + elementCount + " , amount of random students was: " + avg);
            System.out.println("The average collision count for this operation was: " + collisionAvg);
        }
        // Display current test to command line
        System.out.println("Bad hashcode table has this many elements stored: " + bad.size());
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);
        matlabAVGcoll.append("]");
        System.out.println(matlabAVGcoll);

        // =========================== Medium Hashes Test ===========================
        System.out.println("\n\nTesting Adding Medium Hashes");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");
        matlabAVGcoll = new StringBuilder();
        matlabAVGcoll.append("Matlab AVG collision arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            var collisionCount = 0;
            for (i = 0; i < timeToLoop; i++) {
                medium = new HashTable<>();
                for (int j = 0; j < elementCount; j++) {
                    var student = new StudentMediumHash(generateRandomUID(), generateRandomName(), "none");
                    var key = student.hashCode();
                    // Start
                    var start = System.nanoTime();
                    if (medium.put(key, student) != null) // Check for collision in basic arr
                        collisionCount++;
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }
            }
            // Average Calculation
            long avg = sum / timeToLoop;
            var collisionAvg = collisionCount / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            matlabAVGcoll.append(collisionAvg + " ");
            // Display current iteration to command line
            System.out.println("Medium hashcode table has this many elements stored: " + medium.size());
            System.out.println("The time to insert an " + elementCount + " , amount of random students was: " + avg);
            System.out.println("The average collision count for this operation was: " + collisionAvg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);
        matlabAVGcoll.append("]");
        System.out.println(matlabAVGcoll);

        // =========================== Good Hashes Test ===========================
        System.out.println("\n\nTesting Adding Good Hashes");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");
        matlabAVGcoll = new StringBuilder();
        matlabAVGcoll.append("Matlab AVG collision arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            var collisionCount = 0;
            for (i = 0; i < timeToLoop; i++) {
                good = new HashTable<>();
                for (int j = 0; j < elementCount; j++) {
                    var student = new StudentGoodHash(generateRandomUID(), generateRandomName(), "none");
                    var key = student.hashCode();
                    // Start
                    var start = System.nanoTime();
                    if (good.put(key, student) != null) // Check for collision in basic arr
                        collisionCount++;
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }
            }
            // Average Calculation
            long avg = sum / timeToLoop;
            var collisionAvg = collisionCount / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            matlabAVGcoll.append(collisionAvg + " ");
            // Display current iteration to command line
            System.out.println("Good hashcode table has this many elements stored: " + good.size());
            System.out.println("The time to insert an " + elementCount + " , amount of random students was: " + avg);
            System.out.println("The average collision count for this operation was: " + collisionAvg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);
        matlabAVGcoll.append("]");
        System.out.println(matlabAVGcoll);

        // =========================== Custom Int Hashes Test ===========================
        System.out.println("\n\nTesting Adding Int Custom Table");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");
        matlabAVGcoll = new StringBuilder();
        matlabAVGcoll.append("Matlab AVG collision arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            var collisionCount = 0;
            for (i = 0; i < timeToLoop; i++) {
                justInts = new HashTable<>();
                for (int j = 0; j < elementCount; j++) {
                    // Start
                    var start = System.nanoTime();
                    var id = generateRandomUID();
                    if (justInts.put(id, id) != null) // Check for collision in basic arr
                        collisionCount++;
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }
            }
            // Average Calculation
            long avg = sum / timeToLoop;
            var collisionAvg = collisionCount / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            matlabAVGcoll.append(collisionAvg + " ");
            // Display current iteration to command line
            System.out.println("The time to insert an " + elementCount + " , amount of random students was: " + avg);
            System.out.println("The average collision count for this operation was: " + collisionAvg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);
        matlabAVGcoll.append("]");
        System.out.println(matlabAVGcoll);

        // =========================== Java Int Hashes Test ===========================
        System.out.println("\n\nTesting Adding Int Java Map");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");
        matlabAVGcoll = new StringBuilder();
        matlabAVGcoll.append("Matlab AVG collision arr: [");
        var javaTable = new HashMap<Integer, Integer>();


        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            var collisionCount = 0;
            for (i = 0; i < timeToLoop; i++) {
                javaTable = new HashMap<>();
                for (int j = 0; j < elementCount; j++) {
                    // Start
                    var start = System.nanoTime();
                    var id = generateRandomUID();
                    if (javaTable.put(id, id) != null) // Check for collision in basic arr
                        collisionCount++;
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }
            }
            // Average Calculation
            long avg = sum / timeToLoop;
            var collisionAvg = collisionCount / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            matlabAVGcoll.append(collisionAvg + " ");
            // Display current iteration to command line
            System.out.println("The time to insert an " + elementCount + " , amount of random students was: " + avg);
            System.out.println("The average collision count for this operation was: " + collisionAvg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);
        matlabAVGcoll.append("]");
        System.out.println(matlabAVGcoll);
    }

}

