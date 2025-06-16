package Practice.PracticeGraph.Dijkstra;

import java.util.*;

public class MaxMinPath {
    /*
     * You're given an m x n grid of integers grid. Each cell contains a value representing its safety level.
     * Your task is to find a path from the top-left to the bottom-right such that the minimum value in the path is as large as possible.
     * You can move up, down, left, or right.
     */

    static class Cell implements Comparable<Cell> {
        int row, col, minVal;

        Cell(int r, int c, int minVal) {
            this.row = r;
            this.col = c;
            this.minVal = minVal;
        }

        public int compareTo(Cell other) {
            return other.minVal - this.minVal;  // MaxHeap based on min value
        }
    }

    public static int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        pq.offer(new Cell(0, 0, grid[0][0]));

        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int r = curr.row, c = curr.col, val = curr.minVal;

            if (r == m - 1 && c == n - 1) return val;
            if (visited[r][c]) continue;
            visited[r][c] = true;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int newMin = Math.min(val, grid[nr][nc]);
                    pq.offer(new Cell(nr, nc, newMin));
                }
            }
        }

        return -1;  // Should not happen for valid grids
    }

    public static void main(String[] args) {
        int[][] grid = {
            {5, 4, 5},
            {1, 2, 6},
            {7, 4, 6}
        };

        System.out.println("Max-Min Path Value: " + maximumMinimumPath(grid)); // Output: 4
    }
}

