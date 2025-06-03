package AssignmentProblems;

import java.util.*;

public class FillTheBoar2 {
    int maxFed = 0;

    public int eaten(int stomach, int[] muffins, int delay) {
        boolean[] used = new boolean[muffins.length];
        dfs(muffins, used, 0, 0, stomach, delay);
        return maxFed;
    }

    private void dfs(int[] muffins, boolean[] used, int sum, int count, int stomach, int delay) {
        if (sum >= stomach) {
            // Add up to `delay` largest unused muffins
            List<Integer> remaining = new ArrayList<>();
            for (int i = 0; i < muffins.length; i++) {
                if (!used[i]) remaining.add(muffins[i]);
            }

            Collections.sort(remaining, Collections.reverseOrder());
            int extra = 0;
            for (int i = 0; i < Math.min(delay, remaining.size()); i++) {
                extra += remaining.get(i);
            }

            maxFed = Math.max(maxFed, sum + extra);
            return;  // do not feed more; hunger is satisfied
        }

        for (int i = 0; i < muffins.length; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(muffins, used, sum + muffins[i], count + 1, stomach, delay);
                used[i] = false;
            }
        }
    }
}


