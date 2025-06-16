package Practice.PracticeGraph.Dijkstra;

import java.util.*;

public class MinCostValidPath {
    /*
     * You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).     
     * You may change the direction of any cell at a cost of 1. 
     * Your goal is to find the minimum cost to make at least one valid path from (0, 0) to (m - 1, n - 1) following the directions.
     * You may move in the direction the current cell points to at 0 cost.
     * You may change the direction of the cell (before moving) at 1 cost.
     * Find the minimum total cost required to reach the destination.
     */

    static class State implements Comparable<State> {
        int row, col, cost;
        State(int r, int c, int cost) {
            this.row = r;
            this.col = c;
            this.cost = cost;
        }

        public int compareTo(State other) {
            return this.cost - other.cost;
        }
    }

    public static int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] cost = new int[m][n];
        for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);
        cost[0][0] = 0;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};  // right, left, down, up

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int r = curr.row, c = curr.col, currCost = curr.cost;
            if (r == m - 1 && c == n - 1) return currCost;

            if (currCost > cost[r][c]) continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dirs[d][0], nc = c + dirs[d][1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int newCost = currCost + (grid[r][c] == d + 1 ? 0 : 1);
                if (newCost < cost[nr][nc]) {
                    cost[nr][nc] = newCost;
                    pq.offer(new State(nr, nc, newCost));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1,1,3},
            {3,2,2},
            {1,1,4}
        };
        System.out.println("Minimum cost: " + minCost(grid)); // Output: 0
    }
}

