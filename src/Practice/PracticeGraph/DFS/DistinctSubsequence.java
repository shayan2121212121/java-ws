package Practice.PracticeGraph.DFS;

import java.util.HashMap;
import java.util.Map;

public class DistinctSubsequence {
    /*
     * Given two strings source and target, return the number of distinct subsequences of sourse which equal target.
     * A subsequence is a new string formed by deleting some (or no) characters 
     * without changing the relative order of the remaining characters.
     */
    //we will start from source[0] and target[0] matching
    //we will have two options for source[i+1] we can use it or ognore it
    //To use it call dfs with source[i+1] and target[j+1]
    //To skip matching it just call with source[i+1]
    
    public static int getDistinctCount(String source, String target){
        //memo to use for give indices of source and target
        Map<String,Integer> memo = new HashMap<>();
        //call and return dfs result
        return dfs(0, 0, source, target, memo);
    }

    //dfs
    public static int dfs(int sourceIndex, int targetIndex, String source, String target, Map<String,Integer> memo){
        //target found
        if(targetIndex == target.length()) return 1;
        //target not found and source exhausted
        if(sourceIndex == source.length()) return 0;

        //check mwmo
        String key = sourceIndex+","+targetIndex;
        if(memo.containsKey(key))return memo.get(key);

        //count var
        int count = 0;

        //if match use it
        if(source.charAt(sourceIndex)==target.charAt(targetIndex)){
            count += dfs(sourceIndex+1, targetIndex+1, source, target, memo);
        }
        //skip in both match and mismatch case
        count+=dfs(sourceIndex+1, targetIndex, source, target, memo);

        //add to memo
        memo.put(key, count);
        return count;
    }

    public static void main(String[] args) {
        String s = "raabbit";
        String t = "rabbit";
        System.out.println("Number of distinct subsequences: " + getDistinctCount(s, t));
    }

}
