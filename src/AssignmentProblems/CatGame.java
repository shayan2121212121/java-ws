package AssignmentProblems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CatGame {
    public int getNumber(int[] coordinates, int X) {
        Set<Integer> positions = new HashSet<>();
        
        // Step 1: Get all 2*n possible positions
        for (int c : coordinates) {
            positions.add(c - X);
            positions.add(c + X);
        }

        List<Integer> posList = new ArrayList<>(positions);
        int answer = Integer.MAX_VALUE;

        // Step 2: Try all pairs (min, max)
        for (int i = 0; i < posList.size(); i++) {
            for (int j = 0; j < posList.size(); j++) {
                int low = posList.get(i);
                int high = posList.get(j);
                if (low >= high) continue;

                boolean allFit = true;
                for (int c : coordinates) {
                    int left = c - X;
                    int right = c + X;
                    if (!((left >= low && left <= high) || (right >= low && right <= high))) {
                        allFit = false;
                        break;
                    }
                }

                if (allFit) {
                    answer = Math.min(answer, high - low);
                }
            }
        }

        return answer;
    }
}

