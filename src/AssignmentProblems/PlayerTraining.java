package AssignmentProblems;

import java.util.*;

public class PlayerTraining {

    static class Node implements Comparable<Node> {
        int level;
        int gain;

        public Node(int level, int gain) {
            this.level = level;
            this.gain = gain;
        }

        public int compareTo(Node other) {
            return Integer.compare(other.gain, this.gain);
        }
    }

    public long maxStrength(int[] count, int[] strength, int trainDays) {
        int n = count.length;
        long totalStrength = 0;
        for (int i = 0; i < n; i++) {
            totalStrength += 1L * count[i] * strength[i];
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            int gain = strength[i + 1] - strength[i];
            if (gain > 0 && count[i] > 0) {
                pq.add(new Node(i, gain));
            }
        }

        while (trainDays > 0 && !pq.isEmpty()) {
            Node top = pq.poll();
            int level = top.level;
            int gain = top.gain;

            // Train one player from this level
            count[level]--;
            count[level + 1]++;
            totalStrength += gain;
            trainDays--;

            // add another nod for remaining player
            if (count[level] > 0) {
                pq.add(new Node(level, gain));
            }
        }

        return totalStrength;
    }

}
