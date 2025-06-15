package Practice.PracticeAlgo;

public class StockWithFee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;

        int hold = -prices[0]; // max profit holding stock after day 0
        int cash = 0;          // max profit not holding stock after day 0

        for (int i = 1; i < n; i++) {
            int prevHold = hold;
            hold = Math.max(hold, cash - prices[i]);          // either keep holding or buy today
            cash = Math.max(cash, prevHold + prices[i] - fee); // either keep cash or sell today
        }

        return cash; // max profit when not holding stock after last day
    }

    public static void main(String[] args) {
        StockWithFee solver = new StockWithFee();

        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int fee1 = 2;
        System.out.println(solver.maxProfit(prices1, fee1)); // Output: 8

        int[] prices2 = {1, 3, 7, 5, 10, 3};
        int fee2 = 3;
        System.out.println(solver.maxProfit(prices2, fee2)); // Output: 6
    }
}

