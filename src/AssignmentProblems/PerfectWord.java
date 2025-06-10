package AssignmentProblems;

public class PerfectWord {
    public int longest(String W) {
        int n = W.length();
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // each character itself can be a sequence of length 1
            for (int j = 0; j < i; j++) {
                if (W.charAt(j) <= W.charAt(i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // For quick testing
    public static void main(String[] args) {
        PerfectWord pw = new PerfectWord();
        System.out.println(pw.longest("ABCDEFG"));   // 7
        System.out.println(pw.longest("GFEDCBA"));   // 1
        System.out.println(pw.longest("ACBB"));      // 3
        System.out.println(pw.longest("ACCCCBB"));   // 5
    }
}

