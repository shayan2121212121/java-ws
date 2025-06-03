package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Given an unweighted graph represented as an adjacency list of type Map<Integer, List<Integer>>
 * Given a source node src and a destination node dest, 
 * use BFS to find the shortest path (in terms of number of edges) between the two nodes.
 */
public class ShortestPathBFS {

    public static int bfs(Map<Integer,List<Integer>> graph, int start, int dest){
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visiterd = new HashSet<>();
        Map<Integer,Integer> distanceMap = new HashMap<>();

        queue.add(start);
        visiterd.add(start);
        distanceMap.put(start, 0);

        while(!queue.isEmpty()){
            //get current node from queue
            int currNode = queue.remove();

            //check if current is destination node
            if(currNode == dest){
                return distanceMap.get(currNode);
            }
            //adding children to queue set and map
            List<Integer> childrenOfCurrNode = graph.get(currNode);
            for(int i : childrenOfCurrNode){
                if(!visiterd.contains(i)){
                    queue.add(i);
                    visiterd.add(i);
                    distanceMap.put(i, distanceMap.get(currNode)+1);
                }
            }

        }
        return -1;//not possible to reach destination
        
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(1, 2, 4));
        graph.put(4, new ArrayList<>());

        int src = 0, dest = 4;
        int result = bfs(graph, src, dest);
        if (result != -1) {
            System.out.println("Shortest path length is " + result);
        } else {
            System.out.println("Destination not reachable from source.");
        }
    }

}
