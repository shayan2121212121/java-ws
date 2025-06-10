package Practice.PracticeAlgo;

import java.util.Arrays;

public class RaceCar {

    public  int minSteps(int target){
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        dp[0]=0;
        return minSteps(target,dp);
    }

    public  int minSteps(int target, int[]dp){
        if(dp[target]>=0) return dp[target];

        dp[target]=Integer.MAX_VALUE;

        int x=1, j=1;

        for(; j<target; j = (int)Math.pow(2, ++x) - 1){
            for(int q=0, p=0; p<j; p=(int)Math.pow(2, ++q)-1){
                dp[target] = Math.min(dp[target], x+1+1+q+minSteps(target-(j-p), dp));
            }
        }
        int steps;
        if (target == j) {
            steps = x;
        } else {
            steps = x + 1 + minSteps(j - target, dp);
        }
        
        dp[target] = Math.min(dp[target], steps);
        return dp[target];
    }

    public static void main(String[] args) {
        RaceCar tm = new RaceCar();

        System.err.println(tm.minSteps(9));

    }

}
