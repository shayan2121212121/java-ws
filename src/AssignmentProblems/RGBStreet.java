package AssignmentProblems;

public class RGBStreet {

    public int estimateCost(String[] houses) {
        int n = houses.length;
        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] parts = houses[i].split(" ");
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(parts[j]);
            }
        }
        
        // DP array 
        int[][] dp = new int[n][3];
        
        // Base case
        for (int j = 0; j < 3; j++) {
            dp[0][j] = cost[0][j];
        }

       
        for (int i = 1; i < n; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]); // Red
            dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]); // Green
            dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]); // Blue
        }

        
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }
}
