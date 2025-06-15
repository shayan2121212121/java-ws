package Practice.PracticeAlgo;

import java.util.*;

public class GardenNoise {
    public int countDucks(String s) {
        char[] order = {'q', 'u', 'a', 'c', 'k'};
        List<Integer> ducks = new ArrayList<>();
        int maxDucks = 0;

        for (char ch : s.toCharArray()) {
            boolean assigned = false;
            for (int i = 0; i < ducks.size(); i++) {
                int stage = ducks.get(i);
                if (order[stage] == ch) {
                    if (stage == 4) {
                        ducks.remove(i); // quack finished, free this duck
                        i--; // adjust index
                    } else {
                        ducks.set(i, stage + 1); // progress to next stage
                    }
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                if (ch == 'q') {
                    ducks.add(1); // new duck starting at 'u'
                    maxDucks = Math.max(maxDucks, ducks.size());
                } else {
                    return -1; // no duck can accept this char
                }
            }
        }

        // All ducks should have finished saying "quack"
        if (!ducks.isEmpty()) return -1;

        return maxDucks;
    }

    // Test
    public static void main(String[] args) {
        GardenNoise gn = new GardenNoise();
        System.out.println(gn.countDucks("quqacukqauackck")); // 2
        System.out.println(gn.countDucks("kcauq")); // -1
        System.out.println(gn.countDucks("quackquackquackquackquackquackquackquackquackquack")); // 1
        System.out.println(gn.countDucks("qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk")); // 10
        System.out.println(gn.countDucks("quqaquuacakcqckkuaquckqauckack")); // 3
        System.out.println(gn.countDucks("quackqauckquack")); // -1
    }
}


