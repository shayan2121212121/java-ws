package AssignmentProblems;

import java.util.*;

public class FriendlySequences {
    public int count(int[] array) {
        int n = array.length;
        int result = 0;

        // Preprocess: create a fingerprint for each number
        String[] fingerprints = new String[n];
        for (int i = 0; i < n; i++) {
            fingerprints[i] = normalize(array[i]);
        }

        // Try all contiguous subsequences
        for (int start = 0; start < n; start++) {
            Set<String> seen = new HashSet<>();
            seen.add(fingerprints[start]);

            for (int end = start + 1; end < n; end++) {
                // If fingerprint of current is not same as already seen, break
                if (!seen.contains(fingerprints[end]) && seen.size() > 0 && !seen.iterator().next().equals(fingerprints[end])) {
                    break;
                }

                seen.add(fingerprints[end]);

                // All elements so far are friendly
                result++;
            }
        }

        return result;
    }

    // Convert number to a normalized string of its unique digits in sorted order
    private String normalize(int num) {
        boolean[] digitUsed = new boolean[10];
        while (num > 0) {
            digitUsed[num % 10] = true;
            num /= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (digitUsed[i]) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    // Optional: for testing
    public static void main(String[] args) {
        FriendlySequences fs = new FriendlySequences();

        System.out.println(fs.count(new int[]{112, 12, 21, 354, 534345, 345, 2221})); // 6
        System.out.println(fs.count(new int[]{10, 1100, 10101, 111, 1111, 11111, 11, 1, 111})); // 18
        System.out.println(fs.count(new int[]{0, 0, 0, 0})); // 6
        System.out.println(fs.count(new int[]{123456890, 213456890, 198654320})); // 3
        System.out.println(fs.count(new int[]{9})); // 0
    }
}

