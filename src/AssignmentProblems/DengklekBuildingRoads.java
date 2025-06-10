package AssignmentProblems;

import java.util.*;

public class DengklekBuildingRoads {
    static final int MOD = 1_000_000_007;
    int N, M, K;
    List<int[]> edges = new ArrayList<>();
    Map<String, Integer> memo = new HashMap<>();

    public int numWays(int n, int m, int k) {
        N = n; M = m; K = k;
        edges.clear(); memo.clear();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= i + K && j < N; j++) {
                edges.add(new int[]{i, j});
            }
        }

        int[] degree = new int[N];
        return dfs(0, M, degree);
    }

    private int dfs(int pos, int rem, int[] degree) {
        if (rem == 0) {
            for (int d : degree) {
                if (d % 2 != 0) return 0;
            }
            return 1;
        }
        if (pos == edges.size()) return 0;

        String key = pos + "," + rem + "," + encode(degree);
        if (memo.containsKey(key)) return memo.get(key);

        int res = 0;
        int[] edge = edges.get(pos);
        int u = edge[0], v = edge[1];

        // Try adding this edge from 0 to rem times
        for (int cnt = 0; cnt <= rem; cnt++) {
            degree[u] += cnt;
            degree[v] += cnt;

            int sub = dfs(pos + 1, rem - cnt, degree);

            res = (res + sub) % MOD;

            degree[u] -= cnt;
            degree[v] -= cnt;
        }

        memo.put(key, res);
        return res;
    }

    private String encode(int[] deg) {
        StringBuilder sb = new StringBuilder();
        for (int d : deg) sb.append(d % 2);
        return sb.toString();
    }

    // Test
    public static void main(String[] args) {
        DengklekBuildingRoads solver = new DengklekBuildingRoads();
        //System.out.println(solver.numWays(3, 4, 1)); // Expected: 3
        //System.out.println(solver.numWays(4, 3, 3)); // Expected: 4
        System.out.println(solver.numWays(2, 1, 1)); // Expected: 0
        //System.out.println(solver.numWays(5, 0, 3)); // Expected: 1
        //System.out.println(solver.numWays(10, 20, 5)); // Expected: 26964424
    }
}




