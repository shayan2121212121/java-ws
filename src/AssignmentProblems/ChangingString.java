package AssignmentProblems;

import java.util.*;

public class ChangingString {
    public int distance(String A, String B, int K) {
        int n = A.length();
        int[] differences = new int[n];
        int totalDistance = 0;

        // Compute the absolute differences
        for (int i = 0; i < n; i++) {
            differences[i] = Math.abs(A.charAt(i) - B.charAt(i));
            totalDistance += differences[i];
        }

        // Sort the differences in descending order
        Arrays.sort(differences);
        int index = n - 1;  // Start from the largest difference

        // Minimize the total distance by changing the K largest differences
        while (K > 0 && index >= 0) {
            if (differences[index] > 0) {
                totalDistance -= differences[index]; // Reduce the distance
                differences[index] = 0; // Make it zero
            } else {
                totalDistance += 1; // Flip a character arbitrarily (worst case scenario)
            }
            index--;
            K--;
        }

        return totalDistance;
    }

}

