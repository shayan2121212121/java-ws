package Practice.PracticeGraph.DFS;

import java.util.*;

public class WaterJug {

    static class State {
        int jug1;
        int jug2;

        State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof State) {
                State s = (State) o;
                return this.jug1 == s.jug1 && this.jug2 == s.jug2;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(jug1, jug2);
        }
    }

    public static boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int target) {
        //step1 : create queue
        Queue<State> queue = new ArrayDeque<>();//create rqueue
        Set<State> visited = new HashSet<>();//set to track visited states of jugs
        
        //Step2: add root to queue
        queue.add(new State(0, 0)); //add root
        visited.add(new State(0, 0));//add root state to set

        //Step3 while loop till queue is not empty
        while (!queue.isEmpty()) {

            //step1 get curr node
            State curr = queue.remove();

            //Step2:  Check the target if found return true
            if (curr.jug1 == target || curr.jug2 == target || curr.jug1 + curr.jug2 == target) {
                return true;
            }

            //Step3: add all possible children to queue here it will be 3 states of each jugs total six
            // so create a list of these states and add it to queue if state is not in visited set
            List<State> nextStates = new ArrayList<>();

            // Fill jug1
            nextStates.add(new State(jug1Capacity, curr.jug2));

            // Fill jug2
            nextStates.add(new State(curr.jug1, jug2Capacity));

            // Empty jug1
            nextStates.add(new State(0, curr.jug2));

            // Empty jug2
            nextStates.add(new State(curr.jug1, 0));

            // Pour jug1 -> jug2
            int pour1to2 = Math.min(curr.jug1, jug2Capacity - curr.jug2);
            nextStates.add(
                new State(curr.jug1 - pour1to2, curr.jug2 + pour1to2)
                );

            // Pour jug2 -> jug1
            int pour2to1 = Math.min(curr.jug2, jug1Capacity - curr.jug1);
            nextStates.add(
                new State(curr.jug1 + pour2to1, curr.jug2 - pour2to1)
                );

            for (State next : nextStates) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return false;
    }


}
