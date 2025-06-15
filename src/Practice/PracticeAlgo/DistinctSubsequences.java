package Practice.PracticeAlgo;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base case: An empty t can always be formed by any prefix of s
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        DistinctSubsequences solver = new DistinctSubsequences();
        System.out.println(solver.numDistinct("rabbbit", "rabbit")); // Output: 3
        System.out.println(solver.numDistinct("babgbag", "bag"));    // Output: 5
    }
}
