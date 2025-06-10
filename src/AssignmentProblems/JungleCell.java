package AssignmentProblems;

import java.util.*;

public class JungleCell {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public int countHedges(List<String> jungle) {
        int R = jungle.size();
        int C = jungle.get(0).length();
        char[][] grid = new char[R][C];

        for (int i = 0; i < R; i++)
            grid[i] = jungle.get(i).toCharArray();

        boolean[][] reachableFromStart = bfs(grid, R, C, 0, 0);
        boolean[][] reachableFromEnd = bfs(grid, R, C, R - 1, C - 1);

        int count = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '#') {
                    boolean canReachStart = false;
                    boolean canReachEnd = false;

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (inBounds(nr, nc, R, C)) {
                            if (reachableFromStart[nr][nc])
                                canReachStart = true;
                            if (reachableFromEnd[nr][nc])
                                canReachEnd = true;
                        }
                    }

                    if (canReachStart && canReachEnd)
                        count++;
                }
            }
        }

        return count;
    }

    private boolean[][] bfs(char[][] grid, int R, int C, int sr, int sc) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();

        if (grid[sr][sc] == '.')
            q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (inBounds(nr, nc, R, C) && !visited[nr][nc] && grid[nr][nc] == '.') {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return visited;
    }

    private boolean inBounds(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    // Test runner
    public static void main(String[] args) {
        JungleCell jc = new JungleCell();

        System.out.println(jc.countHedges(Arrays.asList("..#", ".#.", "#.."))); // 3
        System.out.println(jc.countHedges(Arrays.asList("..##..", "..##..", "...#..", "..##.."))); // 1
        System.out.println(jc.countHedges(Arrays.asList("..##..", "..##..", "..##..", "..##.."))); // 0
        System.out.println(jc.countHedges(Arrays.asList(
                ".....#.",
                ".###.#.",
                ".#.#...",
                ".###.##",
                ".#.#.#.",
                ".#.###.",
                "##....."
        ))); // 6
    }
}

