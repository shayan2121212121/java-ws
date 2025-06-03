package AssignmentProblems;

public class UnsealTheSafe {


    public long countPasswords(int N){
        
        int[][] adj = {
            {7},        // 0
            {2, 4},     // 1
            {1, 3, 5},  // 2
            {2, 6},     // 3
            {1, 5, 7},  // 4
            {2, 4, 6, 8}, // 5
            {3, 5, 9},  // 6
            {0, 4, 8},  // 7
            {5, 7, 9},  // 8
            {6, 8}      // 9
        };
        long[][] dp = new long[10][N + 1];

        // Base case: 1-digit sequences
        for (int digit = 0; digit <= 9; digit++) {
            dp[digit][1] = 1;
        }

        // Fill dp for lengths 2 to N
        for (int len = 2; len <= N; len++) {
            for (int digit = 0; digit <= 9; digit++) {
                for (int neighbor : adj[digit]) {
                    dp[digit][len] += dp[neighbor][len - 1];
                }
            }
        }

        // Sum all counts of N-length sequences starting with any digit
        long total = 0;
        for (int digit = 0; digit <= 9; digit++) {
            total += dp[digit][N];
        }

        return total;
    }


}
