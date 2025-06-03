package Practice.PracticeGraph.DFS;


import java.util.*;

public class First {
    //DFS is used to travel along a single path to the end in a graph/tree then it backtracks recursiviely
    //problems like word search, sudoku solver, subsets, permutations, etc. where we need to check all possibilities
    //
    private Map<Integer, List<Integer>> adjList;

    public First() {
        adjList = new HashMap<>();
    }

    // Add edge to the graph
    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
        adjList.get(v).add(u); // For undirected graph
    }

    // DFS utility function
    private void dfs(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : adjList.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    // DFS traversal starting from a given node
    public void performDFS(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfs(start, visited);
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        First graph = new First();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        graph.performDFS(0); // Start DFS from node 0
    }
}

