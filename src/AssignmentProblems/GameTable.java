package AssignmentProblems;

public class GameTable {

    public int maxArea(String[] board) {
        int rows = board.length;
        int cols = board[0].length();

        // Build patternA and patternB matrices
        boolean[][] patternA = new boolean[rows][cols];
        boolean[][] patternB = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char cell = board[r].charAt(c);
                char expectedA;
                if ((r + c) % 2 == 0) {
                    expectedA = '0';
                } else {
                    expectedA = '1';
                }
                char expectedB;
                if ((r + c) % 2 == 0) {
                    expectedB = '1';
                } else {
                    expectedB = '0';
                }

                patternA[r][c] = (cell == expectedA);
                patternB[r][c] = (cell == expectedB);
            }
        }

        // Find largest rectangle in patternA and patternB
        int maxA = largestRectangle(patternA);
        int maxB = largestRectangle(patternB);

        return Math.max(maxA, maxB);
    }

    // Helper: largest rectangle in binary matrix
    private int largestRectangle(boolean[][] matrix) {
        int maxArea = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];

        for (int r = 0; r < rows; r++) {
            // Build histogram for this row
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c]) {
                    heights[c]++;
                } else {
                    heights[c] = 0;
                }
            }
            // Calculate max rectangle in histogram for current heights
            maxArea = Math.max(maxArea, largestRectangleInHistogram(heights));
        }

        return maxArea;
    }

    // Largest rectangle in histogram (classic problem)
    private int largestRectangleInHistogram(int[] heights) {
        int maxArea = 0;
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }


    // For quick testing:
    public static void main(String[] args) {
        GameTable gt = new GameTable();

        System.out.println(gt.maxArea(new String[]{"1", "0"})); // 2
        System.out.println(gt.maxArea(new String[]{"0000"})); // 1
        System.out.println(gt.maxArea(new String[]{"01"})); // 2
        System.out.println(gt.maxArea(new String[]{"001", "000", "100"})); // 2
        System.out.println(gt.maxArea(new String[]{"0"})); // 1
        System.out.println(gt.maxArea(new String[]{"101", "010"})); // 6
        System.out.println(gt.maxArea(new String[]{"101", "011", "101", "010"})); // 8
        /*System.out.println(gt.maxArea(new String[]{
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101"}));*/ // 7
        /*System.out.println(gt.maxArea(new String[]{
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "0101010",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101",
                "1010101"}));*/// 21
    }
}

