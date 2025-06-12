package Practice.PracticeGraph.Tree;

import java.util.*;

public class CourseScheduleFour {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] prereq = new boolean[numCourses][numCourses];

        // Step 1: Populate direct prerequisites
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            prereq[u][v] = true;
        }

        // Step 2: Transitive closure using Floyd-Warshall
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    if (prereq[i][k] && prereq[k][j]) {
                        prereq[i][j] = true;
                    }
                }
            }
        }

        // Step 3: Answer queries
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            result.add(prereq[u][v]);
        }

        return result;
    }

    // Test
    public static void main(String[] args) {
        CourseScheduleFour cp = new CourseScheduleFour();

        int[][] prerequisites1 = {{1, 0}};
        int[][] queries1 = {{0, 1}, {1, 0}};
        System.out.println(cp.checkIfPrerequisite(2, prerequisites1, queries1)); // [false, true]

        int[][] prerequisites2 = {};
        int[][] queries2 = {{1, 0}, {0, 1}};
        System.out.println(cp.checkIfPrerequisite(2, prerequisites2, queries2)); // [false, false]

        int[][] prerequisites3 = {{1, 2}, {1, 0}, {2, 0}};
        int[][] queries3 = {{1, 0}, {1, 2}};
        System.out.println(cp.checkIfPrerequisite(3, prerequisites3, queries3)); // [true, true]
    }
}


