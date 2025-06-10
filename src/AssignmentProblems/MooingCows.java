package AssignmentProblems;

public class MooingCows {
    public int dissatisfaction(String[] farmland) {
        int rows = farmland.length;
        int cols = farmland[0].length();
        // Store all cow positions
        java.util.List<int[]> cows = new java.util.ArrayList<>();

        // Collect positions of all cows
        for (int i = 0; i < rows; i++) {
            String row = farmland[i];
            for (int j = 0; j < cols; j++) {
                if (row.charAt(j) == 'C') {
                    cows.add(new int[]{i, j});
                }
            }
        }

        int minDissatisfaction = Integer.MAX_VALUE;

        // Try letting each cow moo, and calculate the total dissatisfaction
        for (int[] mooingCow : cows) {
            int x = mooingCow[0];
            int y = mooingCow[1];
            int total = 0;

            for (int[] otherCow : cows) {
                if (otherCow[0] == x && otherCow[1] == y) continue; // skip self
                int dx = x - otherCow[0];
                int dy = y - otherCow[1];
                total += dx * dx + dy * dy;
            }

            minDissatisfaction = Math.min(minDissatisfaction, total);
        }

        return minDissatisfaction;
    }

    // Optional: main method to test
    public static void main(String[] args) {
        MooingCows mc = new MooingCows();
        System.out.println(mc.dissatisfaction(new String[]{"C..", ".C.", ".C."})); // 3
        System.out.println(mc.dissatisfaction(new String[]{"CCCC", "CCCC", "CCCC"})); // 26
        System.out.println(mc.dissatisfaction(new String[]{"C"})); // 0
        System.out.println(mc.dissatisfaction(new String[]{
            "CCC....",
            "C......",
            "....C.C",
            ".C.CC..",
            "C......"
        })); // 81
    }
}

