package AssignmentProblems;

import java.util.*;

public class TomekPhone {
    public int minKeystrokes(int[] frequencies, int[] keySizes) {
        // Sort frequencies in descending order
        Arrays.sort(frequencies);
        int N = frequencies.length;
        int[] sortedFrequencies = new int[N];
        for (int i = 0; i < N; i++) {
            sortedFrequencies[i] = frequencies[N - 1 - i];
        }

        // Create a list of available keypress positions
        List<Integer> positions = new ArrayList<>();
        for (int keySize : keySizes) {
            for (int i = 1; i <= keySize; i++) {
                positions.add(i); // i-th press on this key
            }
        }

        // Not enough positions to fit all letters
        if (positions.size() < frequencies.length) return -1;

        // Sort positions in ascending order
        Collections.sort(positions);

        // Assign most frequent letters to least costly keypresses
        int totalKeystrokes = 0;
        for (int i = 0; i < frequencies.length; i++) {
            totalKeystrokes += sortedFrequencies[i] * positions.get(i);
        }

        return totalKeystrokes;
    }

    // Example test
    public static void main(String[] args) {
        TomekPhone tp = new TomekPhone();
        System.out.println(tp.minKeystrokes(new int[]{7,3,4,1}, new int[]{2,2})); // 19
        System.out.println(tp.minKeystrokes(new int[]{13,7,4,20}, new int[]{2,1})); // -1
        System.out.println(tp.minKeystrokes(new int[]{11,23,4,50,1000,7,18}, new int[]{3,1,4})); // 1164
        System.out.println(tp.minKeystrokes(new int[]{100,1000,1,10}, new int[]{50})); // 1234
        System.out.println(tp.minKeystrokes(
            new int[]{1,2,3,4,5,6,7,8,9,10,
                      11,12,13,14,15,16,17,18,19,20,
                      21,22,23,24,25,26,27,28,29,30,
                      31,32,33,34,35,36,37,38,39,40,
                      41,42,43,44,45,46,47,48,49,50}, 
            new int[]{10,10,10,10,10,10,10,10})); // 3353
    }
}
