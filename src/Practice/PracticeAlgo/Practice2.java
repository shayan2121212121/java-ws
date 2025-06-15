package Practice.PracticeAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Practice2 {

    //minimum cos of stair climbing
    public int minCostToClimb(int[] cost){
        int n = cost.length;
        //dp array to store minimum cost to reach step
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i<n; i++){
            dp[i] = cost[i] + Math.min(dp[i-2], dp[i-1]);
        }
        return Math.min(dp[n-2], dp[n-1]);

    }

    //coin change problem to find minimum no of coins neeed to achieve a target amount
    public int minCoinNeeded(int[] coins, int amount){

        int[] dp = new int[1+amount];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for( int i = 1; i<=amount; i++){
            for (int j = 0; j<coins.length; j++){
                if(coins[j]==i){
                    dp[i] = 1;
                    break;
                } else if(coins[j]<i && dp[i-coins[j]]!= Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i],1+dp[i-coins[j]]);
                }
            }
        }
        if(dp[amount]==Integer.MAX_VALUE){
            return -1;
        } else{
            return dp[amount];
        }
    }

    //no of ways to decoding encoded string "2123"
    public int numOfWaysToDecode(String str){
        if(str.isEmpty() || str.charAt(0)==0){
            return 0;
        }
        int[] dp = new int[str.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i =2; i<str.length()+1; i++){
            int lastDigit = str.charAt(i-1)-'0';
            int lastTwoDigits = Integer.parseInt(str.substring(i-2, i));

            if(lastDigit>0 && lastDigit<=9){
                dp[i] = dp[i-1];
            }
            if(lastTwoDigits>9 && lastTwoDigits<=26){
                dp[i]= dp[i]+dp[i-2];
            }
        }
        return dp[str.length()];
    }

    //pascals triangle
    public List<List<Integer>> pascalTriangle(int n){
        List<List<Integer>> resuList = new ArrayList<>();
        List<Integer> rowOne = new ArrayList<>();
        rowOne.add(1);
        resuList.add(rowOne);

        for(int r =1; r<n; r++){
            List<Integer> prevRow = resuList.get(r-1);
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            for(int j = 1; j<r; j++){
                curRow.add(prevRow.get(j-1)+prevRow.get(j));
            }
            curRow.add(1);
            resuList.add(curRow);

        }
        return resuList;
    }

    //if possible to create word from dictionary of words
    public boolean wordBreak(String word, List<String> wordDict){

        boolean[] dp = new boolean[word.length()+1];
        Arrays.fill(dp, false);
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict);

        for(int i = 1; i<=word.length();i++){
            for(int j = i-1; j>=0; j--){
                if(dp[j]&&wordSet.contains(word.substring(j,i)));{
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[word.length()];
    }

    //count ways to construct word from array of strings
    public int countConstruct(String target, String[] wordBank){
        int[] dp = new int[target.length()+1];
        Arrays.fill(dp, 0);
        dp[0]=1;
        for(int i = 0; i<=target.length();i++){
            for(String word: wordBank){
                if(target.substring(i, i+word.length()).equals(word)){
                    dp[i+word.length()] = dp[i+word.length()] + dp[i];
                }
            }
        }
        return dp[target.length()];
    }

    //allConstruct function to return 2D array of all the ways target word can be created from wordbank
    public String[][] allConstruct(String target, String[] wordBank){
        // Table where each element is a list of lists of strings (ways to construct substrings)
        List<List<List<String>>> table = new ArrayList<>();

        // Initialize table
        for (int i = 0; i <= target.length(); i++) {
            table.add(new ArrayList<>());
        }

        // Base case: empty string has one way (empty list)
        table.get(0).add(new ArrayList<>());

        for (int i = 0; i <= target.length(); i++) {
            for (String word : wordBank) {
                // If word matches the substring starting at position i
                if (i + word.length() <= target.length() &&
                    target.substring(i, i + word.length()).equals(word)) {

                    List<List<String>> newCombinations = new ArrayList<>();

                    // Add current word to the end of each combination at table[i]
                    for (List<String> sublist : table.get(i)) {
                        List<String> newList = new ArrayList<>(sublist);
                        newList.add(word);
                        newCombinations.add(newList);
                    }

                    // Add new combinations to table[i + word.length()]
                    table.get(i + word.length()).addAll(newCombinations);
                }
            }
        }

        // Convert List<List<String>> to String[][]
        List<List<String>> result = table.get(target.length());
        String[][] resultArray = new String[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i).toArray(new String[0]);
        }

        return resultArray;
    }

    //Combination Sum primary method 
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        combinationSumBacktrack(target, currentCombination, candidates, result, 0);
        return result;
    }

    //Combination sum backtrack method
    private void combinationSumBacktrack(int target, List<Integer> currentCombination, int[] candidates,
    List<List<Integer>> result, int index){
        if(target == 0){
            result.add(new ArrayList<>(currentCombination));
        }

        if(target<0){
            return;
        }

        for(int i = 0; i< candidates.length; i++){
            currentCombination.add(candidates[i]);
            combinationSumBacktrack(target-candidates[i], currentCombination, candidates, result, i);
            currentCombination.remove(currentCombination.size()-1);
        }
    }

    //House robber 1
    public int robHouseOne(int[] num){
        //At every house check if we rob it will profit be greater than the profit if we had robbed the previous house,
        //So keep track of two profits case1 previous house robbed and case 2 previous to previous house robbed.

        int profit = 0; // to maintain max profit
        int lastRobProfit = 0; //profit if house before current was robbed
        int secondLastRobProfit = 0; //profit if house before previous house was robbed e.g. house at 0 for i = 2

        for(int i = 0; i<num.length; i++){
            profit = Math.max(secondLastRobProfit + num[i], lastRobProfit);//max profit if we choose i or not
            secondLastRobProfit =lastRobProfit; //previous to previous becomes previous
            lastRobProfit = profit; //previous becomes current max profit
        }

        return profit;
    }

    //House robber 2 circular arrangement
    public int robHouseTwo(int[] num){
        //same as robHouseOne but we can't select first and last house so we weill do the iteration twice once from 0 to n-2 then 1 to n-1
        int profit1 = 0;
        int lastHouseRobProfit = 0;
        int secondLastHouseRobProfit = 0;

        for(int i =0; i<num.length-1; i++){
            profit1 = Math.max(lastHouseRobProfit, secondLastHouseRobProfit + num[i]);
            secondLastHouseRobProfit = lastHouseRobProfit;
            lastHouseRobProfit = profit1;
        }

        lastHouseRobProfit =0;
        secondLastHouseRobProfit = 0;
        int profit2 =0;

        for(int i =1; i<num.length; i++){
            profit2 = Math.max(lastHouseRobProfit, secondLastHouseRobProfit+num[i]);
            secondLastHouseRobProfit = lastHouseRobProfit;
            lastHouseRobProfit = profit2;
        }

        return Math.max(profit1, profit2);
    }

    //Unique paths to reach last cell of a m*n matrix from 0,0 if can only move right or down
    public int numOfUniqueWays(int m, int n){

        int[][] ways = new int[m][n];

        for (int[] r : ways){
            Arrays.fill(r, 1);
        }
        for(int r = 1; r<m; r++){
            for(int c =1; c<n; c++ ){
                ways[r][c] = ways[r-1][c] + ways[r][c-1];
            }
        }
        return ways[m-1][n-1];
    }

    //generating parantheses combinations given n pairs of open and close paranthesis
    public List<String> generateParantheses(int n){
        List<String> result = new ArrayList<>();
        //call below backtrack method
        generateParanthesesBacktrack(n, new StringBuilder(), 0, 0, result);
        return result;
    }

    private void generateParanthesesBacktrack(int max, StringBuilder currSb, int openCount, int closeCount, List<String> result){
        //add string to list when all parantheses are used
        if(currSb.length()==max*2){
            result.add(currSb.toString());
        }

        if(openCount<max){
            currSb.append("(");
            generateParanthesesBacktrack(max, currSb, openCount+1, closeCount, result);
            currSb.deleteCharAt(currSb.length()-1);
        }
        if(closeCount<openCount){
            currSb.append(")");
            generateParanthesesBacktrack(max, currSb, openCount, closeCount+1, result);
            currSb.deleteCharAt(currSb.length()-1);
        }
    }

    //Jump game can jump max element value from index i return if possible to reach last term
    public boolean canJumpToLast(int[] num){
        boolean [] dp = new boolean[num.length];
        Arrays.fill(dp, false);
        dp[num.length-1]=true;

        for(int i = num.length-2; i>=0; i--){
            if(num[i]>0){
                for(int j = 1; j<=num[i]; j++){
                    if(dp[i+j]){
                        dp[i] = true;
                        break;
                    }
                }
            }
            
        }
        return dp[0];

    }

    //jump game 2 given jumping to last index is possible return minimum no of jumps needed to reach last
    public int minJumpsToLast(int[] num){
        int[] dp = new int[num.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[num.length-1] = 0;

        for(int i = num.length-2; i>=0; i--){
            if(num[i]>0){
                for(int j = 1; j<=num[i]; j++){
                    dp[i] = Math.min(dp[i], 1+dp[i+j]);
                }
            }
            
        }
        return dp[0];
    }

    //find length of longest increasing subsequence from inout int[]
    public int maxSequemceLength(int[] num){
        int res = 0;
        int[] dp = new int[num.length];
        Arrays.fill(dp,1);
        for(int i = 1; i< num.length; i++){
            for(int j = 0; j<i;j++){
                if(num[j]<num[i]){
                    dp[i] = Math.max(dp[i],1+dp[j]);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }


}
