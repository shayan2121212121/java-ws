package Practice.PracticeGraph.Dijkstra;

import java.util.*;

public class MinTimeToReachDestination {
    /*
     * start from the top-left cell (0, 0) at time 0. Every move to an adjacent cell (up, down, left, right) takes 1 unit of time. 
     * You can only move into a cell at time t if t >= grid[r][c] and 
     * (t - grid[r][c]) % 2 == 0 (i.e., you may have to wait to satisfy parity).
     * Your goal is to reach the bottom-right cell (m - 1, n - 1) in the minimum time possible.
     * Return the minimum time to reach the destination, or -1 if it's not possible.
     */

    static class Cell implements Comparable<Cell> {
        int row, col, time;
        Cell(int r, int c, int t) {
            this.row = r;
            this.col = c;
            this.time = t;
        }

        public int compareTo(Cell other) {
            return this.time - other.time;
        }
    }

    public static int minimumTime(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(0, 0, 0));

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int r = curr.row, c = curr.col, t = curr.time;

            if (r == m - 1 && c == n - 1) return t;
            if (visited[r][c]) continue;
            visited[r][c] = true;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) continue;

                int waitTime = Math.max(grid[nr][nc], t + 1);
                if ((waitTime - grid[nr][nc]) % 2 != 0) waitTime++; // adjust for parity

                pq.offer(new Cell(nr, nc, waitTime));
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 2, 4},
            {1, 3, 6},
            {2, 4, 8}
        };
        System.out.println("Minimum Time: " + minimumTime(grid));  // Output: 8
    }
}

