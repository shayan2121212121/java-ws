package Practice.PracticeGraph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class First {

    static class Edge {
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        } 
    }

    static class Node implements Comparable<Node> {

        int vertex,distance;

        Node(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance-o.distance;
        }
    }

    public static int[] dijkstraShortestPath(int v, List<List<Edge>> adjecencyList, int source){
        //array to store shortest distance for each vertex
        int[] distanceArray = new int[v];
        Arrays.fill(distanceArray, Integer.MAX_VALUE); //initiate with max value
        //make source value 0
        distanceArray[source]=0;

        //create priority queue and add source
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        //processing the graph
        while (!pq.isEmpty()) {
            //get current node
            Node currNode = pq.poll();
            //current vertex
            int u = currNode.vertex;

            //loop through the list of edges for current vertex from adjacent list and update minimum distance in dist array
            for(Edge edge: adjecencyList.get(u)){
                int neighborVertex = edge.to;
                int neighborWeight = edge.weight;

                //check if distance of curr node + neighborweight is < distance of neighborNode
                if(distanceArray[u] + neighborWeight< distanceArray[neighborVertex]){
                    distanceArray[neighborVertex] = distanceArray[u] + neighborWeight;
                    pq.add(new Node(neighborVertex, distanceArray[neighborVertex])); //add neighboe to queue
                } 
            }
            
        }

        return distanceArray;

    }
    public static void main(String[] args) {
        int V = 3;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Adding edges: 0 -> 1 (1), 1 -> 2 (1), 0 -> 2 (4)
        graph.get(0).add(new Edge(1, 1));
        graph.get(1).add(new Edge(2, 1));
        graph.get(0).add(new Edge(2, 4));

        int source = 0;
        int[] distances = dijkstraShortestPath(V, graph, source);

        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To " + i + " = " + distances[i]);
        }
    }
}
