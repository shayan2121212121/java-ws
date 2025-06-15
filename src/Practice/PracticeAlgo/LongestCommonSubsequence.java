package Practice.PracticeAlgo;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // dp[i][j] will hold the length of LCS of text1[0..i-1] and text2[0..j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Build the dp array from bottom up
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // If characters match, add 1 to the result from previous indices
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Else take max from either removing char from text1 or text2
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The bottom-right cell will contain the length of LCS for entire strings
        return dp[n][m];
    }

    // Example usage
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        
        System.out.println(lcs.longestCommonSubsequence("abcde", "ace")); // Output: 3
        System.out.println(lcs.longestCommonSubsequence("abc", "abc"));   // Output: 3
        System.out.println(lcs.longestCommonSubsequence("abc", "def"));   // Output: 0
    }
}

