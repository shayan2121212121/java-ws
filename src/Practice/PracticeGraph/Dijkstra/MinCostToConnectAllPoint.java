package Practice.PracticeGraph.Dijkstra;

import java.util.PriorityQueue;

public class MinCostToConnectAllPoint {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        boolean[] inMST = new boolean[n]; // Whether a point is already in the MST

        // Start with the first point
        pq.offer(new Point(0, 0));

        int minCost = 0;
        int pointsConnected = 0;
        while (pointsConnected < n) {
            Point current = pq.poll();
            if (inMST[current.index]) {
                continue; // Skip if the point is already in the MST
            }
            inMST[current.index] = true;
            minCost += current.distance;
            pointsConnected++;

            // Update the priority queue with distances to the new point in the MST
            for (int i = 0; i < n; i++) {
                if (!inMST[i]) {
                    int distance = Math.abs(points[current.index][0] - points[i][0]) + Math.abs(points[current.index][1] - points[i][1]);
                    pq.offer(new Point(i, distance));
                }
            }
        }

        return minCost;
    }

    static class Point {
        int index;
        int distance;

        Point(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

}
