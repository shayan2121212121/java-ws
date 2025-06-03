package AssignmentProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FamilyTravel {
    //Dijkstra problem so first create Node class
    static class Node implements Comparable<Node> {
        int from, city, time, interval;
        Node(int from, int city, int time, int interval){
            this.from=from;
            this.city=city;
            this.time= time;
            this.interval=interval;
        }

        @Override
        public int compareTo(Node o) {
            return this.time-o.time;
        }
    }
    public static int shortest(String[] edges){

        //Step1: Create and fill graph
        Map<Integer,List<int[]>> graph = new HashMap<>();
        for(int i=0;i<edges.length;i++){
            for(int j=0; j<edges[0].length();j++){
                if(i!=j){//can't go from same city to same city
                    int fromCity = i;
                    int toCity = j;
                    int time = 0;
                    char c = edges[i].charAt(j);
                    if(c!='0'){//can't travel to city where time is 0
                        if (c >= 'a' && c <= 'z') {
                            time= c - 'a' + 1; 
                        } else if (c >= 'A' && c <= 'Z') {
                            time= c - 'A' + 27; 
                        }
                    
                        if(!graph.containsKey(i)){
                            graph.put(fromCity, new ArrayList<>());
                        }
                        graph.get(fromCity).add(new int[]{toCity,time});
                    }
                    
                }
                
            }
        }
        //Step 2: dist array for min weight with initial max value
        int[][] costArr = new int[edges.length][53];
        for (int[] row : costArr) Arrays.fill(row, Integer.MAX_VALUE);
        costArr[0][52] = 0; // Start at city 0 with "infinite" last interval (root)

        //Step 3: queue set with root
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0,53));

        while(!pq.isEmpty()){
            //Step 4:get currNode
            Node currNode = pq.poll();

            //Step 5: get neighbors using graph
            List<int[]> neighbors = graph.getOrDefault(currNode.city, new ArrayList<>());
            for(int[] neighbor: neighbors){
                int nextCity = neighbor[0];
                int nextCityTime = neighbor[1];
                if(currNode.time+nextCityTime<costArr[nextCity][nextCityTime]){
                        if(nextCityTime<=currNode.interval){//interval check for non root nodes
                            costArr[nextCity][nextCityTime]=currNode.time+nextCityTime;
                            pq.offer(new Node(currNode.city, nextCity, costArr[nextCity][nextCityTime],nextCityTime));
                        }

                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= 52; i++) {
            minCost = Math.min(minCost, costArr[1][i]);
        }
        if(minCost==Integer.MAX_VALUE){
            return -1;
        } else {
            return minCost;
        }
    }
    public static void main(String[] args) {
        String[] edges = {"00kHmcsPRq", "0000w0vW00", "k00gwi00AI", "H0g00R00Oh", "mww00CpsqE", "c0iRC000Y0", "sv00p000v0", "PW00s00000", "R0AOqYv00N", "q0IhE000N0"};
        int result = shortest(edges);
        System.out.println("Network delay time: " + result); // Output: 2
    }

}
