package AssignmentProblems;

import java.util.*;

public class WarehouseRenovation {
    public int countDarkSquares(int R, int C, List<String> roomPlan) {
        boolean[][] lit = new boolean[R][C];
        
        // Convert roomPlan into a grid of characters
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = roomPlan.get(i).toCharArray();
        }

        // Directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'O') {
                    lit[r][c] = true;  // Lamp lights its own square

                    // Explore all 4 directions
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        while (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                            if (grid[nr][nc] == '#') break; // Stop at wall
                            lit[nr][nc] = true;
                            nr += dr[d];
                            nc += dc[d];
                        }
                    }
                }
            }
        }

        // Count unlit empty squares
        int dark = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '.' && !lit[r][c]) {
                    dark++;
                }
            }
        }

        return dark;
    }

    // Optional test runner
    public static void main(String[] args) {
        WarehouseRenovation wr = new WarehouseRenovation();

        System.out.println(wr.countDarkSquares(3, 4, Arrays.asList("....", "....", "...."))); // 12
        System.out.println(wr.countDarkSquares(4, 5, Arrays.asList(".....", ".O...", ".....", "....."))); // 12
        System.out.println(wr.countDarkSquares(4, 5, Arrays.asList(".....", ".OO..", ".....", "....."))); // 9
        System.out.println(wr.countDarkSquares(4, 5, Arrays.asList(".....", ".O#..", ".#.O.", "....."))); // 9
        System.out.println(wr.countDarkSquares(1, 1, Arrays.asList("O"))); // 0
        System.out.println(wr.countDarkSquares(4, 5, Arrays.asList(".....", ".O...", "...O.", "....."))); // 6
    }
}

