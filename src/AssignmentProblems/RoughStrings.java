package AssignmentProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoughStrings {
    public int minRoughness(String s, int n) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int maxFreqInput = 0;
        List<Integer> counts = new ArrayList<>();
        for (int f : freq) {
            if (f > 0) counts.add(f);
            maxFreqInput = Math.max(maxFreqInput, f);
        }

        Collections.sort(counts);
        int result = Integer.MAX_VALUE;

        for (int minFreq = 1; minFreq <= maxFreqInput; minFreq++) {
            for (int maxFreq = minFreq; maxFreq <= maxFreqInput; maxFreq++) {
                int removed = 0;
                int localMin = Integer.MAX_VALUE;
                int localMax = 0;

                for (int f : counts) {
                    if (f < minFreq) {
                        removed += f; // remove all occurrences
                    } else if (f > maxFreq) {
                        removed += f - maxFreq; // trim down to maxFreq
                        localMax = Math.max(localMax, maxFreq);
                        localMin = Math.min(localMin, maxFreq);
                    } else {
                        localMax = Math.max(localMax, f);
                        localMin = Math.min(localMin, f);
                    }
                }

                if (removed <= n) {
                    result = Math.min(result, localMax - localMin);
                }
            }
        }

        return result;
    }

    // Test
    public static void main(String[] args) {
        RoughStrings solver = new RoughStrings();
        //System.out.println(solver.minRoughness("aaaaabbc", 1)); // 3
        //System.out.println(solver.minRoughness("aaaabbbbc", 5)); // 0
        //System.out.println(solver.minRoughness("veryeviltestcase", 1)); // 2
        System.out.println(solver.minRoughness("gggggggooooooodddddddllllllluuuuuuuccckkk", 5)); // 3
        //System.out.println(solver.minRoughness("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", 17)); // 0
        //System.out.println(solver.minRoughness("bbbccca", 2)); // 0
    }
}

