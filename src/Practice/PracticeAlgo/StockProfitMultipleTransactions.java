package Practice.PracticeAlgo;

public class StockProfitMultipleTransactions {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // If selling today is profitable, add that profit
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }

    // Example usage
    public static void main(String[] args) {
        StockProfitMultipleTransactions sp = new StockProfitMultipleTransactions();

        System.out.println(sp.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // Output: 7
        System.out.println(sp.maxProfit(new int[]{1, 2, 3, 4, 5}));    // Output: 4
        System.out.println(sp.maxProfit(new int[]{7, 6, 4, 3, 1}));    // Output: 0
    }
}

