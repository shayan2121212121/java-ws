package AssignmentProblems;

import java.util.*;

public class NumberSplit {

    private Map<Integer, Integer> memo = new HashMap<>();

    public int longestSequence(int start) {
        if (start < 10) return 1;
        if (memo.containsKey(start)) return memo.get(start);

        String s = String.valueOf(start);
        int maxLength = 1;

        List<List<Integer>> allSplits = getAllSplits(s);
        for (List<Integer> split : allSplits) {
            int product = 1;
            for (int num : split) {
                product *= num;
            }
            int sequenceLength = 1 + longestSequence(product);
            maxLength = Math.max(maxLength, sequenceLength);
        }

        memo.put(start, maxLength);
        return maxLength;
    }

    // Generate all ways to split a string of digits
    private List<List<Integer>> getAllSplits(String s) {
        List<List<Integer>> result = new ArrayList<>();
        int n = s.length();
        int totalSplits = (1 << (n - 1)); // 2^(n-1) ways to insert separators

        for (int mask = 1; mask < totalSplits; mask++) { // skip 0
            List<Integer> split = new ArrayList<>();
            int last = 0;
            for (int i = 0; i < n - 1; i++) {
                if ((mask & (1 << i)) != 0) {
                    split.add(Integer.parseInt(s.substring(last, i + 1)));
                    last = i + 1;
                }
            }
            split.add(Integer.parseInt(s.substring(last)));
            result.add(split);
        }

        return result;
    }

}
