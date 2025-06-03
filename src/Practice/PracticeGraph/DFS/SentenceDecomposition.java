package Practice.PracticeGraph.DFS;

import java.util.HashMap;
import java.util.Map;

public class SentenceDecomposition {

    static Map<Integer, Integer> memo;

    public static int decompose(String sentence, String[] validWords) {
        memo = new HashMap<>();

        int cost = dfs(0, sentence,validWords);
        return cost == Integer.MAX_VALUE ? -1 : cost;
    }

    public static int dfs(int index, String sentence, String[] validWords) {
        if (index == sentence.length()) return 0;
        if (memo.containsKey(index)) return memo.get(index);

        int minCost = Integer.MAX_VALUE;

        for (String word : validWords) {
            int len = word.length();
            if (index + len <= sentence.length()) {
                String sub = sentence.substring(index, index + len);
                if (isJumbled(word, sub)) {
                    int cost = getCost(word, sub);
                    int next = dfs(index + len, sentence, validWords);
                    if (next != Integer.MAX_VALUE) {
                        minCost = Math.min(minCost, cost + next);
                    }
                }
            }
        }

        memo.put(index, minCost);
        return minCost;
    }

    //method to check if jumbled
    public static boolean isJumbled(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
    
        int[] count = new int[26]; // for 'a' to 'z'
    
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int index = c - 'a';
            count[index]++;//count char c
        }
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            int index = c - 'a';
            count[index]--;//decrease char c count if present in second string
        }
    
        for (int i = 0; i < 26; i++) {//all counts 0
            if (count[i] != 0) {
                return false;
            }
        }
    
        return true;
    }

    //get cost of transformation
    public static int getCost(String original, String transformed) {
        int cost = 0;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) != transformed.charAt(i)) {
                cost++;
            }
        }
        return cost;
    }
    

}
