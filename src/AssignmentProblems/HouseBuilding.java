package AssignmentProblems;

public class HouseBuilding {
    public static int getMinimum(String[] area) {
        int rows = area.length;
        int cols = area[0].length();
        int minEffort = Integer.MAX_VALUE;

        // Try leveling the ground to height h or h+1 for every h from 0 to 8
        for (int h = 0; h <= 8; h++) {
            int effort = 0;
            
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int height = area[r].charAt(c) - '0'; // Convert char to integer
                    if (height < h) {
                        effort += (h - height);
                    } else if (height > h + 1) {
                        effort += (height - (h + 1));
                    }
                }
            }

            minEffort = Math.min(minEffort, effort);
        }

        return minEffort;
    }
}
