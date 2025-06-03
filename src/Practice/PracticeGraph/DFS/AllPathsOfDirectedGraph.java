package Practice.PracticeGraph.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPathsOfDirectedGraph {
    /*
     * Given a directed acyclic graph (DAG) of n nodes (0 to n-1), 
     * return all possible paths from node 0 to node n - 1.
     * Each node i has a list of nodes it can go to, represented by graph[i].
     * input int[][] graph = [[1,2], [3], [3], []]
     * int[][] output = [[0, 1, 3], [0, 2, 3]]
     */

     //from 0 it can go to 1,2
     //from 1 can go to 3
     //from 2 can go to 3
     //3 is the end node
     //use dfs to travel from 0 to destination 3(last graph index), while storing all nodes in a list
     //For each neighbor of the current node, recursively get all paths from that neighbor to the target.
     //Then, for each of those paths, prepend the current node and add it to the result list.

     public static List<List<Integer>> getAllPaths(int[][] graph){
        //memo to store sub paths to avoid repeat calculation
        Map<Integer,List<List<Integer>>> memo = new HashMap<>();
        //call dfs and return the result from dfs
        return dfs(graph, 0, memo);
     }

     //dfs
     public static List<List<Integer>> dfs(int[][] graph,int node, Map<Integer,List<List<Integer>>> memo){
        //memo check
        if(memo.containsKey(node)) return memo.get(node);

        //result list
        List<List<Integer>> result = new ArrayList<>();

        //destination check, this will be the first List<List<Integer>> when dfs returns bottom up
        if(node == graph.length-1) {
            List<Integer> res = new ArrayList<>();
            res.add(node);
            result.add(res);
        }
        //neighbors of node
        int[] neighbors = graph[node];
        for(int neighbor:neighbors){
            //recursive call: e.g. for node 1 path might be 1-2-3 & 1-3 this will return [[2,3],[3]]
            List<List<Integer>> neighborPaths = dfs(graph, neighbor, memo);
            for(List<Integer> neighborPath: neighborPaths){
                //for each neighborPath create new path and prepend curr node 1
                List<Integer> newPath = new ArrayList<>();//[]
                newPath.add(node);//[1]
                //now append current neighborpath [2,3]
                newPath.addAll(neighborPath);//[1,2,3]
                //add new path to result
                result.add(newPath);//[[1,2,3]...]

            }
        }

        //add in memo
        memo.put(node, result);
        return result;
     }

     public static void main(String[] args) {
        int[][] graph = {
            {1, 2},
            {3},
            {3},
            {}
        };

        List<List<Integer>> paths = getAllPaths(graph);
        System.out.println("All paths from source to target:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }

}
