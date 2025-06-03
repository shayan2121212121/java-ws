package Practice.PracticeAlgo;

import java.util.HashMap;
import java.util.List;

public class DynamicProblemPractice {

    HashMap<Integer,Integer> fibRecMemo = new HashMap<>();

    //Fibonacci series with recurion
    public int fibRec(int n){

        //base case
        if (n<=1) return n;

        //check if answer in memory
        if(this.fibRecMemo.containsKey(n)) return this.fibRecMemo.get(n);

        //recursive call solution
        int result = fibRec(n-1) + fibRec(n - 2);
        
        //save result in map
        this.fibRecMemo.put(n, result);

        return result;

    }

    //Fibonacci series with bottom up using array store only the last two numbers to optimize space.
    public int fibBU(int n){

        //base case
        if(n <= 1) return n;

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;

        for(int i =2; i<=n; i++){
            int lastVal = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = lastVal;
        }
        return dp[1];


    }

    public boolean sumPossible(int amount, List<Integer> numbers){
        return sumPossible(amount, numbers, new HashMap<>());
    }

    public boolean sumPossible(int amount, List<Integer> numbers, HashMap<Integer, Boolean> memo){

        if(amount == 0){
            return true;
        }
        if(amount<0){
            return false;
        }
        if(memo.containsKey(amount)){
            return memo.get(amount);
        }
        for (int num : numbers){
            int subAmount = amount - num;
            if(sumPossible(subAmount, numbers)){
                memo.put(amount, true);
                return true;
            }
        }

        memo.put(amount, false);

        return false;
    }



    public int minCoin(int amount, List<Integer> coins){
        return minCoin(amount, coins, new HashMap<>());
    }
    
    public int minCoin(int amount, List<Integer> coins, HashMap<Integer,Integer> memo){

        if(amount == 0){
            return 0;
        }

        if (amount < 0){
            return -1;
        }

        if(memo.containsKey(amount)){
            return memo.get(amount);
        }

        int minCoins = -1;
        for (int coin : coins){
            int subAmount = amount - coin;
            int subCoins = minCoin(subAmount, coins);
            if(subCoins != -1){
                int numCoins = subCoins + 1;
                if (numCoins < minCoins || minCoins == -1){
                    minCoins = numCoins;
                }
            }
        }

        memo.put(amount, minCoins);
        return minCoins;
    }


    public int countPath(List<List<String>> grid){
        return countPath(0, 0, grid, new HashMap<>());
    }

    
    public int countPath(int r, int c, List<List<String>> grid, HashMap<List<Integer>,Integer> memo){

        if(r == grid.size() || c == grid.get(0).size()){
            return 0;
        }

        if (grid.get(r).get(c).equals("X")){
            return 0;
        }

        if (r == grid.size()-1 && c == grid.get(0).size()-1){
            return 1;
        }

        List<Integer> pos = List.of(r,c);
        if(memo.containsKey(pos)){
            return memo.get(pos);
        }

        int result =  countPath(r+1, c, grid, memo) + countPath(r, c+1, grid,memo);

        memo.put(pos, result);
        return result;
    }

        
    public int noh(int n) {
            
    
            int pairs = n / 2;
            int[] dp = new int[pairs+1];
    
            dp[0] = 1; // Base
    
            for (int i = 1; i <= pairs; i++) {
                dp[i] = 0;
                for (int j = 0; j < i; j++) {
                    dp[i] += dp[j] * dp[i - 1 - j];
                }
            }
    
            return dp[pairs];
        }
    

}
