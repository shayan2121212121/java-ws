package Practice.PracticeAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice1 {

    //climbing stairs - allowed 1 or 2 steps at a time -find no of ways to reach a step n
    public int noOfWaysToClimb(int n) {
        //for any n , no of ways to reach will be sum of ways to reach n-1 and n-2
        //base case will be n =0 or n=1 which will return 1
        if(n==0 || n == 1){
            return 1;
        }
        //recursive case
        return noOfWaysToClimb(n-1) + noOfWaysToClimb(n-2);

    }

    //minimum cost of climbing stairs given int[] cost
    public int minCostOfClimb(int[] costArr) {
        //for any n, minimum cost will be min of cost(n-1) and cost(n-2) + current step cost
        //base case will be for single element cost(0) = costArr[0] and for two wlwmwnts min of cost(0) and costArr[1]
        //dynamic programming approach
        int[] dp = new int[costArr.length];
        dp[0] = costArr[0];
        dp[1] = costArr[1];
        for(int i = 2; i<costArr.length;i++) {

            dp[i] = Math.min(dp[i-1], dp[i-2]) + costArr[i];

        }
        //return the last element of dp[]
        return Math.min(dp[costArr.length-2],dp[costArr.length-1]);
    }

    //given array of coin denomination find minimum number of coins to make a given amount or return -1 of not possible
    public int coinChange(int[] coins, int amount) {
        //using dynamic programming array of size of amount corresponding to each amount store minimum no of coin for that amount
        //for any amount i minimum coins needed will be min(dp[i],1+dp[i-coint[j]]) where dp[i] is the current dp array value
       int[] dp = new int[amount+1]; //dynamic programming array for amount 0 to target amount
       Arrays.fill(dp, Integer.MAX_VALUE); // initiate with max value
       dp[0] = 0; //base case for amount 0 coins needed will be 0
       for(int i = 1; i<=amount; i++) {//for amount 1 to target
        for(int j = 0;j<coins.length;j++) {//for each coin denomination
            if(i>=coins[j]){//if amount is grater than current coin denomination
                dp[i] = Math.min(dp[i], 1+dp[i-coins[j]]);//update dp[i] with min of current value and 1+dp[i-coin[j]]
            }
        }
       }
       if(dp[amount]== Integer.MAX_VALUE) {
        return -1;
       } else {
        return dp[amount];
       }
    }

    //Number of ways to decode an input string - A to Z is 1 to 26. Input e.g. "2123"
    public int numOfWaysToDecode(String encodedStr) {
        //base case for empty or input 0
        if (encodedStr.isEmpty() || encodedStr.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[encodedStr.length()+1];
        dp[0] = 1; // ways to decode empty string 
        dp[1] = 1; //ways to decode single digit i.e. 1st digit
        for (int i = 2; i<=encodedStr.length();i++){
            int lastDigit = Integer.parseInt(encodedStr.substring(i-1, i));
            int lastTwoDigits = Integer.parseInt(encodedStr.substring(i-2, i));

            if(lastDigit<=9 && lastDigit >=1){
                dp[i] = dp[i-1]; //for single last digit
            }
            if(lastTwoDigits>=10 && lastTwoDigits<=26){
                dp[i] = dp[i]+dp[i-2]; //for last two digits if its between 10 and 26
            }
        }
        return dp[encodedStr.length()];//last value of dp[]
    }

    //Pascal's triangle for n rows
    public List<List<Integer>> pascalsTriangle(int n){
        List<List<Integer>> outpList = new ArrayList<>();
        List<Integer> rowOne = new ArrayList<>();
        rowOne.add(1);
        outpList.add(rowOne);

        for(int r = 1; r<n; r++){
            List<Integer> prevRow = outpList.get(r-1);
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);//add 1 will go to last position in the end
            for(int j = 1;j<r; j++){
                currentRow.add(prevRow.get(j)+prevRow.get(j-1));//2nd to 2nd last element population of each row
            }
            currentRow.add(1); //first element
            outpList.add(currentRow);
        }


        return outpList;
    }

}
