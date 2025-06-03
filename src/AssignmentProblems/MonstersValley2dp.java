package AssignmentProblems;

import java.util.Arrays;

public class MonstersValley2dp {

    public int minimumPrice(int[] dread, int[] price) {
        int n = dread.length;
        int maxCost = n * 2; 
        long[][] dp = new long[n + 1][maxCost + 1];
        
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) { //row iteration
            for (int cost = 0; cost <= maxCost; cost++) { //col iteration
                if (dp[i][cost] == -1) continue; 
                
                if (dp[i][cost] >= dread[i]) {
                    dp[i + 1][cost] = Math.max(dp[i + 1][cost], dp[i][cost]);
                }
                
                int newCost = cost + price[i];
                if (newCost <= maxCost) {
                    dp[i + 1][newCost] = Math.max(dp[i + 1][newCost], dp[i][cost] + dread[i]);
                }
            }
        }
        for (int cost = 0; cost <= maxCost; cost++) {
            if (dp[n][cost] != -1) return cost;
        }
        
        return maxCost; // Should never reach here
    }

}
