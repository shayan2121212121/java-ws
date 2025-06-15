package Practice.PracticeAlgo;

public class StockProfit {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;  // Track minimum price seen so far
        int maxProfit = 0;                 // Track max profit so far

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;          // Update min price if current price is lower
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);  // Calculate potential profit and update max profit
            }
        }

        return maxProfit;
    }

    // Example usage
    public static void main(String[] args) {
        StockProfit sp = new StockProfit();

        System.out.println(sp.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // Output: 5
        System.out.println(sp.maxProfit(new int[]{7, 6, 4, 3, 1}));    // Output: 0
    }
}

