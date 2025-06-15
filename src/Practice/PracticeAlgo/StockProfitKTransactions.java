package Practice.PracticeAlgo;

public class StockProfitKTransactions {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        
        // If k is large enough, it's unlimited transactions problem
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1]) {
                    maxProfit += prices[i] - prices[i-1];
                }
            }
            return maxProfit;
        }
        
        int[][] dp = new int[k + 1][n];
        
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0];  // max of dp[i-1][m] - prices[m]
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }
        
        return dp[k][n - 1];
    }

    public static void main(String[] args) {
        StockProfitKTransactions solver = new StockProfitKTransactions();
        
        System.out.println(solver.maxProfit(2, new int[]{2, 4, 1})); // Output: 2
        System.out.println(solver.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); // Output: 7
    }
}

