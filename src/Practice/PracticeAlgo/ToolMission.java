package Practice.PracticeAlgo;

public class ToolMission {
    static final int MOD = 998244353;

    public int planRoutes(int[] minTools, int[] maxTools) {
        int n = minTools.length;

        // Get overall max value we need to track
        int maxVal = 0;
        for (int x : maxTools) maxVal = Math.max(maxVal, x);

        // dp[i][j] = # of sequences of length i+1 ending with j tools
        int[] dp = new int[maxVal + 2];
        int[] prefix = new int[maxVal + 2];

        // Step 0: Initialize dp for first region
        for (int t = minTools[0]; t <= maxTools[0]; t++) {
            dp[t] = 1;
        }

        // Fill prefix for step 0
        prefix[0] = dp[0];
        for (int j = 1; j <= maxVal; j++) {
            prefix[j] = (prefix[j - 1] + dp[j]) % MOD;
        }

        // Process each region
        for (int i = 1; i < n; i++) {
            int[] newDp = new int[maxVal + 2];
            int[] newPrefix = new int[maxVal + 2];

            for (int j = minTools[i]; j <= maxTools[i]; j++) {
                int l = Math.max(minTools[i - 1], minTools[i - 1]);  // previous min
                int r = Math.min(j - 1, maxTools[i - 1]);            // j must be > previous

                if (l <= r) {
                    int ways = prefix[r];
                    if (l > 0) ways = (ways - prefix[l - 1] + MOD) % MOD;
                    newDp[j] = ways;
                }
            }

            // Update prefix for new dp
            newPrefix[0] = newDp[0];
            for (int j = 1; j <= maxVal; j++) {
                newPrefix[j] = (newPrefix[j - 1] + newDp[j]) % MOD;
            }

            dp = newDp;
            prefix = newPrefix;
        }

        // Sum all valid tool counts in final region
        int res = 0;
        for (int j = minTools[n - 1]; j <= maxTools[n - 1]; j++) {
            res = (res + dp[j]) % MOD;
        }

        return res;
    }

    // Test cases
    public static void main(String[] args) {
        ToolMission t = new ToolMission();
        //System.out.println(t.planRoutes(new int[]{1, 3, 1, 4}, new int[]{6, 5, 4, 6})); // 4
        //System.out.println(t.planRoutes(new int[]{30, 10}, new int[]{40, 20})); // 0
        //System.out.println(t.planRoutes(new int[]{10, 30}, new int[]{20, 40})); // 121
        //System.out.println(t.planRoutes(new int[]{35, 20}, new int[]{90, 45})); // 55
        //System.out.println(t.planRoutes(new int[]{4, 46, 46, 35, 20, 77, 20}, new int[]{41, 65, 84, 90, 49, 86, 88})); // 2470
        System.out.println(t.planRoutes(new int[]{1}, new int[]{1000000000})); // 1755647
    }
}

