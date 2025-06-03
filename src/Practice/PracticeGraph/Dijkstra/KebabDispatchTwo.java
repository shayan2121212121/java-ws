package Practice.PracticeGraph.Dijkstra;

import java.util.*;

public class KebabDispatchTwo {

    static class State implements Comparable<State> {
        int r, c, cost;

        State(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};
    int R, C;
    char[][] grid;
    int[][] dist;

    public int calculateMinimumTime(String[] terrain) {
        R = terrain.length;
        C = terrain[0].length();
        grid = new char[R][C];

        int startR = -1, startC = -1;
        List<int[]> buildings = new ArrayList<>();

        // Parse grid
        for (int i = 0; i < R; i++) {
            grid[i] = terrain[i].toCharArray();
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'X') {
                    startR = i;
                    startC = j;
                } else if (grid[i][j] == '$') {
                    buildings.add(new int[]{i, j});
                }
            }
        }

        // Step 1: Dijkstra to find min time from X to all cells
        dist = new int[R][C];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dijkstra(startR, startC);

        int n = buildings.size();
        int[] deliveryTimes = new int[n];

        for (int i = 0; i < n; i++) {
            int[] b = buildings.get(i);
            int d = dist[b[0]][b[1]];
            if (d == Integer.MAX_VALUE) return -1;
            deliveryTimes[i] = d;
        }

        // Step 2: Assign deliveries to 2 delivery men to minimize time
        // Each delivery requires 2 * time (go + return) except last delivery
        // Try all possible assignments using DFS with pruning (binary search)

        Arrays.sort(deliveryTimes);
        int lo = 0, hi = 0;
        for (int t : deliveryTimes) hi += 2 * t;
        
        int res = hi;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canDeliver(deliveryTimes, mid)) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res;
    }

    // Can we deliver all kebabs such that both delivery men finish by 'timeLimit'
    private boolean canDeliver(int[] times, int timeLimit) {
        return assign(times, 0, 0, 0, timeLimit);
    }

    // Try assigning each delivery to either person A or B
    private boolean assign(int[] times, int idx, int aTime, int bTime, int limit) {
        if (idx == times.length) return true;

        int t = times[idx];
        // Try assigning to person A
        if (aTime + (idx == times.length - 1 ? t : 2 * t) <= limit)
            if (assign(times, idx + 1, aTime + (idx == times.length - 1 ? t : 2 * t), bTime, limit))
                return true;

        // Try assigning to person B
        if (bTime + (idx == times.length - 1 ? t : 2 * t) <= limit)
            if (assign(times, idx + 1, aTime, bTime + (idx == times.length - 1 ? t : 2 * t), limit))
                return true;

        return false;
    }

    private void dijkstra(int sr, int sc) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(sr, sc, 0));
        dist[sr][sc] = 0;

        while (!pq.isEmpty()) {
            State s = pq.poll();
            if (s.cost > dist[s.r][s.c]) continue;

            for (int d = 0; d < 4; d++) {
                int nr = s.r + dr[d];
                int nc = s.c + dc[d];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                int moveCost = getCost(s.r, s.c, nr, nc);
                if (moveCost == -1) continue;

                int newCost = s.cost + moveCost;
                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.add(new State(nr, nc, newCost));
                }
            }
        }
    }

    private int getCost(int r1, int c1, int r2, int c2) {
        char ch1 = grid[r1][c1];
        char ch2 = grid[r2][c2];

        if (ch1 == '$' || ch1 == 'X' || ch2 == '$' || ch2 == 'X')
            return 2;

        int h1 = ch1 - '0';
        int h2 = ch2 - '0';
        int diff = Math.abs(h1 - h2);

        if (diff > 1) return -1;
        return diff == 0 ? 1 : 3;
    }
}

