package AssignmentProblems;

public class FoxAndMountainEasy {

    public String possible(int n, int h0, int hn, String history) {
        int len = history.length();

        // Calculate net altitude change and minimum altitude during the history segment
        int delta = 0;
        int minHeightDuringHistory = 0;
        int height = 0;
        for (int i = 0; i < len; i++) {
            if (history.charAt(i) == 'U') height++;
            else height--;
            minHeightDuringHistory = Math.min(minHeightDuringHistory, height);
        }
        delta = height;

        int stepsOutside = n - len;

        // We try every possible number of steps before the history segment (prefixSteps)
        for (int prefixSteps = 0; prefixSteps <= stepsOutside; prefixSteps++) {
            int suffixSteps = stepsOutside - prefixSteps;

            // We want to find if we can arrange prefixSteps and suffixSteps so that:
            // h0 + prefixDelta = starting height before history segment
            // after history, height = (starting height before history) + delta
            // then after suffixSteps, height must be hn

            // The total change outside history is (hn - h0 - delta)
            int totalOutsideDelta = hn - h0 - delta;

            // Check if totalOutsideDelta is achievable within stepsOutside
            if (Math.abs(totalOutsideDelta) > stepsOutside) continue;

            // Check parity: the difference between steps and delta must be even
            if ((stepsOutside - Math.abs(totalOutsideDelta)) % 2 != 0) continue;

            // Calculate the height after prefix steps:
            // Let prefixDelta be the change during prefixSteps
            // Then suffixDelta = totalOutsideDelta - prefixDelta
            // prefixDelta must be in range [-prefixSteps, prefixSteps] and same parity as prefixSteps
            // For this problem, we can guess prefixDelta as totalOutsideDelta - suffixDelta for each suffixDelta

            // But to avoid complexity, since arms length difference can be divided arbitrarily,
            // we can calculate prefixDelta directly:

            // prefixDelta = totalOutsideDelta - suffixDelta
            // suffixDelta must be between -suffixSteps and suffixSteps and have same parity as suffixSteps
            // Instead of brute forcing suffixDelta, we check only prefixDelta = some value so that:
            // h_before_history = h0 + prefixDelta

            // From parity conditions and range, prefixDelta can be chosen as totalOutsideDelta - suffixDelta.

            // Simplify:
            // prefixDelta can be (totalOutsideDelta + stepsOutside) / 2 - suffixSteps, but complicated.

            // Better approach: Since we want to check if there exists some prefixDelta so that
            // h_before_history + minHeightDuringHistory >= 0
            // h_before_history = h0 + prefixDelta

            // Try to choose prefixDelta = between -prefixSteps and prefixSteps with same parity as prefixSteps
            // such that h_before_history + minHeightDuringHistory >= 0

            // Let's try prefixDelta from -prefixSteps to prefixSteps step 2
            for (int prefixDelta = -prefixSteps; prefixDelta <= prefixSteps; prefixDelta += 2) {
                int suffixDelta = totalOutsideDelta - prefixDelta;
                if (suffixDelta < -suffixSteps || suffixDelta > suffixSteps || (suffixDelta % 2 != suffixSteps % 2)) {
                    continue; // suffixDelta invalid
                }

                int hBeforeHistory = h0 + prefixDelta;
                if (hBeforeHistory + minHeightDuringHistory >= 0) {
                    return "YES";
                }
            }
        }
        return "NO";
    }


    // Main method for testing
    public static void main(String[] args) {
        FoxAndMountainEasy solver = new FoxAndMountainEasy();

        //System.out.println(solver.possible(4, 0, 4, "UU")); // YES
        //System.out.println(solver.possible(4, 0, 4, "D"));  // NO
        //System.out.println(solver.possible(4, 100000, 100000, "DDU")); // YES
        //System.out.println(solver.possible(4, 0, 0, "DDU")); // NO
        System.out.println(solver.possible(20, 20, 20, "UDUDUDUDUD")); // YES
        //System.out.println(solver.possible(20, 0, 0, "UUUUUUUUUU")); // YES
        //System.out.println(solver.possible(20, 0, 0, "UUUUUUUUUUU")); // NO
    }
}

