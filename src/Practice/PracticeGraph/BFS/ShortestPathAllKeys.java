package Practice.PracticeGraph.BFS;

import java.util.*;

public class ShortestPathAllKeys {
    static class State {
        int x, y, keys, steps;
        State(int x, int y, int keys, int steps) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.steps = steps;
        }
    }

    public static int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int allKeys = 0;
        int startX = 0, startY = 0;

        // Find the starting position and total number of keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    startX = i;
                    startY = j;
                } else if (c >= 'a' && c <= 'f') {
                    allKeys |= (1 << (c - 'a')); // set bit for each key
                }
            }
        }

        Queue<State> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][64]; // 2^6 = 64 key combinations
        queue.offer(new State(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.keys == allKeys) return curr.steps;

            for (int[] dir : directions) {
                int newX = curr.x + dir[0];
                int newY = curr.y + dir[1];
                int keys = curr.keys;

                if (newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

                char cell = grid[newX].charAt(newY);
                if (cell == '#') continue;

                // If it's a lock and we don't have the key, skip
                if (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) == 0) {
                    continue;
                }

                // If it's a key, collect it
                if (cell >= 'a' && cell <= 'f') {
                    keys |= (1 << (cell - 'a'));
                }

                if (!visited[newX][newY][keys]) {
                    visited[newX][newY][keys] = true;
                    queue.offer(new State(newX, newY, keys, curr.steps + 1));
                }
            }
        }

        return -1; // Impossible to collect all keys
    }

    // Test it
    public static void main(String[] args) {
        String[] grid = {
            "@..#...",
            ".#.#a..",
            ".#...#.",
            "..##...",
            "...b#.."
        };

        int result = shortestPathAllKeys(grid);
        System.out.println("Shortest path to collect all keys: " + result);
    }
}

