package Practice.PracticeGraph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    
    static class Node implements Comparable<Node> {
        int from,to,time;
        Node(int from, int to, int time){
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time-o.time;
        }  
    }

    public static int networkTime(int[][] times, int n, int k){

        //create and populate the graph
        Map<Integer,List<int[]>> graph = new HashMap<>();
        for(int[] time: times){
            int from = time[0];
            int to = time[1];
            int signalTime = time[2];
            if(!graph.containsKey(from)){
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(new int[]{to,signalTime});
        }

        //dist and queue
        int[] dist = new int[n+1];//from 0 to n, 0 will remain max value
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;//start node 
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0,k,0));//start node reach

        while(!pq.isEmpty()){//node = 2,3,1
            Node currNode = pq.poll();
            int currTo = currNode.to;//3

            //no destination so no check
            //get neighbors of curr node using graph
            List<int[]> neighbors = graph.getOrDefault(currTo, new ArrayList<>());//[4,1]
            for(int[] next : neighbors){
                int nextFrom = next[0];//4
                int nextTime = next[1];//1

                if(dist[currTo]+nextTime<dist[nextFrom]){//1+1<Max = 2
                    dist[nextFrom] = dist[currTo]+nextTime;//2
                    pq.offer(new Node(currTo, nextFrom, dist[nextFrom]));//node = 3,4,2
                }
            }

        }
        //check in dist from 1 to n the max time that will be the ans but any max value will mean unvisited node so return -1
        int maxTime =0;
        for(int i =1;i<=n;i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            maxTime = Math.max(maxTime, dist[i]);
        }


        return maxTime;
    }



    public static void main(String[] args) {
        int[][] times = { {2, 1, 1}, {2, 3, 1}, {3, 4, 1} };
        int n = 4;
        int k = 2;
        int result = networkTime(times, n, k);
        System.out.println("Network delay time: " + result); // Output: 2
    }

}
