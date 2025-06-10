package AssignmentProblems;

public class HandsShaking {
    public long countPerfect(int n) {
        int k = n / 2;
        long[] dp = new long[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= k; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[k];
    }

    // Test
    public static void main(String[] args) {
        HandsShaking hs = new HandsShaking();
        System.out.println(hs.countPerfect(2));  // 1
        System.out.println(hs.countPerfect(4));  // 2
        System.out.println(hs.countPerfect(8));  // 14
        System.out.println(hs.countPerfect(10)); // 42
    }
}

