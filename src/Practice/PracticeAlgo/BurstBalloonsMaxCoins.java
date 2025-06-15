package Practice.PracticeAlgo;

public class BurstBalloonsMaxCoins {

    public int maxCoins(int[] nums) {
        int n = nums.length;

        // Pad nums with 1 at both ends
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        // dp[i][j] means the max coins from arr[i] to arr[j], exclusive
        int[][] dp = new int[n + 2][n + 2];

        // Length of the subarray being considered
        for (int len = 2; len <= n + 1; len++) {
            for (int left = 0; left <= n + 1 - len; left++) {
                int right = left + len;

                for (int i = left + 1; i < right; i++) {
                    int coins = arr[left] * arr[i] * arr[right];
                    coins += dp[left][i] + dp[i][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        BurstBalloonsMaxCoins solver = new BurstBalloonsMaxCoins();
        System.out.println(solver.maxCoins(new int[]{3, 1, 5, 8})); // Output: 167
        System.out.println(solver.maxCoins(new int[]{1, 5}));       // Output: 10
    }
}

