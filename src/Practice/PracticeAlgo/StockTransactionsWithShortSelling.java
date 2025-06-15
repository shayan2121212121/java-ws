package Practice.PracticeAlgo;

public class StockTransactionsWithShortSelling {
    public long maxProfit(int[] prices, int k) {
        int n = prices.length;
        long[] pricesL = new long[n];
        for (int i = 0; i < n; i++) pricesL[i] = prices[i]; // convert to long

        // dp[t][day][holding]
        long[][][] dp = new long[k + 1][n][3];

        // initialize with very small numbers
        for (int t = 0; t <= k; t++) {
            for (int day = 0; day < n; day++) {
                dp[t][day][0] = 0L;               // no stock holding no profit
                dp[t][day][1] = Long.MIN_VALUE;  // holding normal stock impossible initially
                dp[t][day][2] = Long.MIN_VALUE;  // holding short stock impossible initially
            }
        }

        // Base cases for day 0
        dp[0][0][1] = -pricesL[0];  // buy normal stock
        dp[0][0][2] = pricesL[0];   // short sell

        for (int day = 1; day < n; day++) {
            for (int t = 0; t <= k; t++) {
                // no position today, rest from yesterday
                dp[t][day][0] = dp[t][day - 1][0];

                // buy normal stock today or hold normal stock
                dp[t][day][1] = dp[t][day - 1][1];
                if (dp[t][day - 1][0] != Long.MIN_VALUE) {
                    dp[t][day][1] = Math.max(dp[t][day][1], dp[t][day - 1][0] - pricesL[day]);
                }

                // short sell today or hold short position
                dp[t][day][2] = dp[t][day - 1][2];
                if (dp[t][day - 1][0] != Long.MIN_VALUE) {
                    dp[t][day][2] = Math.max(dp[t][day][2], dp[t][day - 1][0] + pricesL[day]);
                }

                // sell normal stock today and complete a transaction
                if (t > 0 && dp[t - 1][day - 1][1] != Long.MIN_VALUE) {
                    dp[t][day][0] = Math.max(dp[t][day][0], dp[t - 1][day - 1][1] + pricesL[day]);
                }

                // buy back short today and complete a transaction
                if (t > 0 && dp[t - 1][day - 1][2] != Long.MIN_VALUE) {
                    dp[t][day][0] = Math.max(dp[t][day][0], dp[t - 1][day - 1][2] - pricesL[day]);
                }
            }
        }

        // max profit on last day without holding stock for up to k transactions
        long result = 0L;
        for (int t = 0; t <= k; t++) {
            if (dp[t][n - 1][0] > result) {
                result = dp[t][n - 1][0];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        StockTransactionsWithShortSelling solver = new StockTransactionsWithShortSelling();

        int[] prices = {439905949,666304906,328728050,996405752,379313886,528209791,88582883,939135548,
            751069794,109146128,883868801,685035870,872864534,515610456,671402135,299270187,782796059,
            14959721,863144680,901085624,622229387,536656476,257303050,868839354,117275933,918430202,
            935695732,478547107,484151756,631419928,39696098,650941214,51074234,941181946,265314584,
            557086091,786537782,50596574,28828693,157162091,9857934,451956750,695591748,879988702,
            249629554,539569656,282083076,39183395,66614080,479066152,652564309,907349719,210005879,
            768785742,537258749,237393978,346271286,392541722,312074103,126562356,400828204,614474102,
            364762040,8363356,539354781,90084496,319405489,644955686,889207045,798527610,141688158,
            529097227,598399178,87898767,830035760,49071715,600386530,40425784,322514114,778707680,
            79388396};
        int k = 100;  // example max k (you can adjust)

        System.out.println(solver.maxProfit(prices, k));  // Should match expected large output
    }
}

