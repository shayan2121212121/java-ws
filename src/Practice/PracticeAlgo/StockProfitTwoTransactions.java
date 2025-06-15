package Practice.PracticeAlgo;

public class StockProfitTwoTransactions {
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;

        for (int price : prices) {
            // Max profit after first buy (we want to buy at lowest price)
            firstBuy = Math.max(firstBuy, -price);
            // Max profit after first sell (sell after first buy)
            firstSell = Math.max(firstSell, firstBuy + price);
            // Max profit after second buy (buy again, but deduct profit from firstSell)
            secondBuy = Math.max(secondBuy, firstSell - price);
            // Max profit after second sell (sell after second buy)
            secondSell = Math.max(secondSell, secondBuy + price);
        }

        return secondSell;
    }

    public static void main(String[] args) {
        StockProfitTwoTransactions solver = new StockProfitTwoTransactions();

        System.out.println(solver.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // Output: 6
        System.out.println(solver.maxProfit(new int[]{1, 2, 3, 4, 5}));          // Output: 4
        System.out.println(solver.maxProfit(new int[]{7, 6, 4, 3, 1}));          // Output: 0
    }
}

