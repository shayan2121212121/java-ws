package Practice.PracticeGraph.BFS;

import java.util.*;

public class KnightMinSteps {
    static class Point {
        int x, y, steps;
        Point(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public static int minKnightMoves(int N, int x1, int y1, int x2, int y2) {
        int[][] dp = new int[N][N];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        int[][] dirs = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x1, y1, 0));
        dp[x1][y1] = 0;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == x2 && p.y == y2) return p.steps;

            for (int[] d : dirs) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && dp[nx][ny] > p.steps + 1) {
                    dp[nx][ny] = p.steps + 1;
                    queue.offer(new Point(nx, ny, p.steps + 1));
                }
            }
        }

        return -1; // if unreachable
    }

    public static void main(String[] args) {
        int N = 8;
        int x1 = 0, y1 = 0;
        int x2 = 7, y2 = 7;

        System.out.println("Minimum steps: " + minKnightMoves(N, x1, y1, x2, y2));
    }
}

