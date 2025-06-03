package Practice.PracticeGraph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlight {

    /*
     * Given n cities
     * A list of flights, where each flight is represented as flights[i] = [from, to, price]
     * Source and destination as src and dst and k (maximum number of stops allowed)
     * Return the cheapest price from src to dst with at most k stops.
     */
    
    static class Flight implements Comparable<Flight> {
        int city,cost,stops;
        Flight(int city, int cost, int stops){
            this.city=city;
            this.cost = cost;
            this.stops = stops;
        }

        @Override
        public int compareTo(Flight o) {
            return this.cost - o.cost;
        }
    
        
    }
    public static int cheapestFlights(int n, int[][] flights, int src, int dst, int k){
        //build the graph
        Map<Integer,List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }

            graph.get(from).add(new int[] {to, cost});
        }

        //create priority queue
        PriorityQueue<Flight> pq = new PriorityQueue<>();
        //add root
        pq.offer(new Flight(src, 0, 0));

        // Track best known cost to each city with <= k stops
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[src] = 0;

        while(!pq.isEmpty()){
            //curr flight
            Flight currFlight = pq.poll();

            //check if destination
            if(currFlight.city == dst){
                return currFlight.cost;
            }

            //check for stops limit
            if(currFlight.stops>k) continue;

            //get neighbors from graph if no value for the city add empty list instead to avoid null exception
            List<int[]> neighbors = graph.getOrDefault(currFlight.city, new ArrayList<>());
            //loop through neighbors to add in queue
            for(int[] neighbor: neighbors){
                int nextCity = neighbor[0];
                int nextCost = currFlight.cost + neighbor[1];

                if(nextCost<visited[nextCity]){
                    //add in set
                    visited[nextCity] = nextCost;
                    //add in queue
                    pq.offer(new Flight(nextCity, nextCost, currFlight.stops+1));
                }
            }
        }
        return -1;
        
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 3, 100},
            {0, 2, 500}
        };
        int src = 0, dst = 3, k = 1;

        int result = cheapestFlights(n, flights, src, dst, k);
        System.out.println("Cheapest price: " + result); // Output: 600
    }

}
