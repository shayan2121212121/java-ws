package Practice.PracticeAlgo;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        // DP filling
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j], // delete
                        Math.min(
                            dp[i][j - 1], // insert
                            dp[i - 1][j - 1] // replace
                        )
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        EditDistance solver = new EditDistance();
        System.out.println(solver.minDistance("horse", "ros"));        // Output: 3
        System.out.println(solver.minDistance("intention", "execution")); // Output: 5
    }
}

