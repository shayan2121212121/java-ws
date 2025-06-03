package AssignmentProblems;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ForestGarbage {

    public int[] bestWay(String[] forest) {
        int rows = forest.length;
        int cols = forest[0].length();
        int[] dx = {0, 1, 0, -1}; // right, down, left, up
        int[] dy = {1, 0, -1, 0};

        class Cell {
            int x, y, garbage, near;
            Cell(int x, int y, int garbage, int near) {
                this.x = x;
                this.y = y;
                this.garbage = garbage;
                this.near = near;
            }
        }

        // find start and end
        int startX = -1, startY = -1, endX = -1, endY = -1;
        boolean[][] adjToGarbage = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = forest[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'F') {
                    endX = i;
                    endY = j;
                }
            }
        }

        // Mark adjacent cells 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (forest[i].charAt(j) == 'g') {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {//oob cond
                            if (forest[ni].charAt(nj) == '.') {
                                adjToGarbage[ni][nj] = true;
                            }
                        }
                    }
                }
            }
        }

        int[][][] best = new int[rows][cols][2];
        for (int[][] arr : best)
            for (int[] a : arr)
                Arrays.fill(a, Integer.MAX_VALUE);

        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> {
            if (a.garbage != b.garbage)
                return Integer.compare(a.garbage, b.garbage);
            return Integer.compare(a.near, b.near);
        });

        pq.add(new Cell(startX, startY, 0, 0));
        best[startX][startY][0] = 0;
        best[startX][startY][1] = 0;

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            if (curr.x == endX && curr.y == endY) {
                return new int[]{curr.garbage, curr.near};
            }

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if (nx < 0 || nx >= rows || ny < 0 || ny >= cols)//out of bound cond
                    continue;

                char nextChar = forest[nx].charAt(ny);
                int g = curr.garbage;
                int n = curr.near;

                if (nextChar == 'g') g++;
                else if (nextChar == '.' && adjToGarbage[nx][ny]) n++;

                if (g < best[nx][ny][0] || (g == best[nx][ny][0] && n < best[nx][ny][1])) {
                    best[nx][ny][0] = g;
                    best[nx][ny][1] = n;
                    pq.add(new Cell(nx, ny, g, n));
                }
            }
        }

        return new int[]{-1, -1}; 
    }
}
