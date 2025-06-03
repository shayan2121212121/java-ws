package AssignmentProblems;

import java.util.*;

public class CellRemoval {
    public int cellsLeft(int[] parent, int deletedCell) {
        int n = parent.length;
        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        //{-1, 0, 0, 1, 1}

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                root = i;
            } else {
                tree.get(parent[i]).add(i);
            }
        }

        return countLeaves(tree, root, deletedCell);
    }
    /*
     * tree = [
  [1, 2],  // node 0 has children 1 and 2
  [3, 4],  // node 1 has children 3 and 4
  [],      // node 2 has no children
  [],      // node 3 has no children
  []       // node 4 has no children
]

     */

    private int countLeaves(List<List<Integer>> tree, int current, int deletedCell) {
        if (current == deletedCell) {
            return 0;
        }

        int count = 0;
        boolean hasChild = false;

        for (int child : tree.get(current)) {
            if (child != deletedCell) {
                hasChild = true;
                count += countLeaves(tree, child, deletedCell);
            }
        }

        if (!hasChild) {
            return 1; // Current node is a leaf (or becomes a leaf because its children are deleted)
        } else {
            return count;
        }
    }

}

