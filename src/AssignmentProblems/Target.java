package AssignmentProblems;

import java.util.ArrayList;
import java.util.List;

public class Target {

    public int getTarget(int target) {
        int res = 0;
        List<Integer> remList = new ArrayList<>();
        while(target/2 >= 2) {
            target = target/2;
            int rem = target %2;
            remList.add(rem);
        }
        return res;
    }
    
}
