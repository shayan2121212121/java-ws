package Practice.PracticeAlgo;

public class StockWithCooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        if (n == 1) return 0;

        int[] dp0 = new int[n]; // not holding stock
        int[] dp1 = new int[n]; // holding stock

        dp0[0] = 0;
        dp1[0] = -prices[0];

        dp0[1] = Math.max(dp0[0], dp1[0] + prices[1]);
        dp1[1] = Math.max(dp1[0], -prices[1]);

        for (int i = 2; i < n; i++) {
            dp0[i] = Math.max(dp0[i - 1], dp1[i - 1] + prices[i]);
            dp1[i] = Math.max(dp1[i - 1], dp0[i - 2] - prices[i]);
        }

        return dp0[n - 1];
    }

    public static void main(String[] args) {
        StockWithCooldown solver = new StockWithCooldown();

        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println(solver.maxProfit(prices1)); // Output: 3

        int[] prices2 = {1};
        System.out.println(solver.maxProfit(prices2)); // Output: 0
    }
}

