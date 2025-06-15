package Practice.PracticeAlgo;

import java.util.Arrays;

public class BurstBalloonsArrows {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

        // Sort by end point
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int prevEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            // If current balloon starts after the last arrow's position
            if (points[i][0] > prevEnd) {
                arrows++;
                prevEnd = points[i][1];
            }
        }

        return arrows;
    }

    public static void main(String[] args) {
        BurstBalloonsArrows solver = new BurstBalloonsArrows();
        System.out.println(solver.findMinArrowShots(new int[][]{
            {10,16},{2,8},{1,6},{7,12}
        })); // Output: 2

        System.out.println(solver.findMinArrowShots(new int[][]{
            {1,2},{3,4},{5,6},{7,8}
        })); // Output: 4

        System.out.println(solver.findMinArrowShots(new int[][]{
            {1,2},{2,3},{3,4},{4,5}
        })); // Output: 2
    }
}

