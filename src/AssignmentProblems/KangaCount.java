package AssignmentProblems;

public class KangaCount {

    // Memoization cache
    private static double[][][] memo = new double[21][6][21];
    private static boolean[][][] visited = new boolean[21][6][21];

    public static double howProbable(int estimatedK, int caughtPerDay, int daysNumber, int markedK) {
        for (int i = 0; i <= estimatedK; i++) {
            for (int j = 0; j <= daysNumber; j++) {
                for (int k = 0; k <= estimatedK; k++) {
                    visited[i][j][k] = false;
                }
            }
        }
        return dfs(estimatedK, caughtPerDay, daysNumber, 0, markedK);
    }

    private static double dfs(int total, int perDay, int daysLeft, int marked, int targetMarked) {
        if (daysLeft == 0) {
            return marked == targetMarked ? 1.0 : 0.0;
        }

        if (visited[total][daysLeft][marked]) {
            return memo[total][daysLeft][marked];
        }

        visited[total][daysLeft][marked] = true;
        double prob = 0.0;

        // Count how many unmarked kangaroos are left
        int unmarked = total - marked;

        // Try catching k unmarked kangaroos out of caughtPerDay total
        for (int k = 0; k <= Math.min(unmarked, perDay); k++) {
            int caughtMarked = perDay - k;
            if (caughtMarked > marked) continue; // Can't catch more marked than available

            // Compute probability of catching k unmarked and (perDay - k) marked kangaroos
            double p = hypergeometric(total, marked, perDay, caughtMarked);

            // Recursively continue for next day with k new marked kangaroos
            prob += p * dfs(total, perDay, daysLeft - 1, marked + k, targetMarked);
        }

        memo[total][daysLeft][marked] = prob;
        return prob;
    }

    // Hypergeometric distribution
    private static double hypergeometric(int N, int K, int n, int x) {
        // N: total population
        // K: total marked
        // n: total drawn
        // x: marked drawn
        // => C(K, x) * C(N-K, n-x) / C(N, n)
        return comb(K, x) * comb(N - K, n - x) / comb(N, n);
    }

    private static double comb(int n, int k) {
        if (k < 0 || k > n) return 0.0;
        double res = 1.0;
        for (int i = 1; i <= k; i++) {
            res *= (n - (k - i));
            res /= i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(howProbable(3, 1, 2, 2)); // 0.6666666666666666
        System.out.println(howProbable(3, 1, 5, 1)); // 0.012345679012345678
        System.out.println(howProbable(8, 3, 3, 7)); // 0.2582908163265306
        System.out.println(howProbable(5, 3, 2, 4)); // 0.6
        System.out.println(howProbable(20, 6, 5, 17)); // 0.30035494805367574
    }
}

