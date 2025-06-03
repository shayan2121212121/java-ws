package AssignmentProblems;

import java.util.HashMap;
import java.util.Map;

public class QuickSums {

    private Map<String, Integer> memo;
    
    public int minSums(String numbers, int target) {
        memo = new HashMap<>();
        int result = dfs(numbers, 0, 0, 0, target);
        if (result == Integer.MAX_VALUE) {
            return -1;
        } else {
            return result;
        }
    }
    
    private int dfs(String numbers, int index, long currentSum, int additions, int target) {
        if (index == numbers.length()) {
            if (currentSum == target) {
                return additions;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        
        String key = index + "," + currentSum + "," + additions;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int minAdditions = Integer.MAX_VALUE;
        for (int end = index; end < numbers.length(); end++) {
            long num = Long.parseLong(numbers.substring(index, end + 1));
            int newAdditions = additions;
            if (index != 0) {
                newAdditions += 1;
            }
            minAdditions = Math.min(minAdditions, dfs(numbers, end + 1, currentSum + num, newAdditions, target));
        }
        
        memo.put(key, minAdditions);
        return minAdditions;
    }
        
      

}
