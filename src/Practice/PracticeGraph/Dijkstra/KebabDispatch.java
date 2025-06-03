package Practice.PracticeGraph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;



    /*
     * given a grid each square either contains a building or is empty
     * Each empty square has an integer height between 0 and 9, inclusive.
     * You can only move between two empty squares if the absolute difference of their heights is less than or equal to 1.
     * if the height difference is 0, it takes 1 minute to make the move
     * if the absolute height difference is 1, it takes 3 minutes.
     * You can always move to a building from any of its adjacent squares
     * Moving to or from a square containing a building takes 2 minutes. 
     * that the kebab place itself is also a building.
     * each building in the area has ordered one kebab, 
     * and Peter must use his two delivery mans to fulfil all the orders in the shortest total amount of time possible.
     * Each delivery man can only carry one kebab at a time. This means that after each delivery, 
     * the delivery man must return to the kebab place to pick up another kebab if there are more deliveries left to do.
     *  given a String[] terrain, where the j-th character of the i-th element represents the square at row i, 
     * column j of the terrain.
     * Return the minimum time in minutes at which the last delivery can be made or -1
     * $' represents a building from which a kebab was ordered
     * 'X' represents the location of the restaurant,
     * the digits '0'-'9' represent the heights of empty squares.
     */

public class KebabDispatch {
        
        public static class State implements Comparable<State> {
            int x, y, time;
    
            State(int x, int y, int time) {
                this.x = x;
                this.y = y;
                this.time = time;
            }
    
            public int compareTo(State other) {
                return Integer.compare(this.time, other.time);
            }
        }
    
        // Directions: up, down, left, right
        private static final int[] dx = {-1, 1, 0, 0};
        private static final int[] dy = {0, 0, -1, 1};
    
        public int calculateMinimumTime(String[] terrain) {
            int rows = terrain.length;
            int cols = terrain[0].length();
    
            char[][] grid = new char[rows][cols];
            int[][] height = new int[rows][cols];
    
            int sx = -1, sy = -1; // Restaurant position
            List<int[]> targets = new ArrayList<>();
    
            // Parse the grid
            for (int i = 0; i < rows; i++) {
                grid[i] = terrain[i].toCharArray();
                for (int j = 0; j < cols; j++) {
                    char ch = grid[i][j];
                    if (ch == 'X') {
                        sx = i;
                        sy = j;
                    } else if (ch == '$') {
                        targets.add(new int[]{i, j});
                    } else {
                        height[i][j] = ch - '0';
                    }
                }
            }
    
            // Dijkstra from restaurant to compute minimum time to all cells
            int[][] dist = new int[rows][cols];
            for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
            dist[sx][sy] = 0;
    
            PriorityQueue<State> pq = new PriorityQueue<>();
            pq.add(new State(sx, sy, 0));
    
            while (!pq.isEmpty()) {
                State current = pq.poll();
                int x = current.x;
                int y = current.y;
                int time = current.time;
    
                if (time > dist[x][y]) continue;
    
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
    
                    if (nx < 0 || nx >= rows || ny < 0 || ny >= cols)
                        continue;
    
                    char from = grid[x][y];
                    char to = grid[nx][ny];
                    int cost = Integer.MAX_VALUE;
    
                    if (from == '$' || from == 'X') {
                        // From building or restaurant
                        if (to == '$' || to == 'X') {
                            cost = 2; // between buildings
                        } else {
                            cost = 2;
                        }
                    } else if (Character.isDigit(from)) {
                        int h1 = height[x][y];
                        if (to == '$' || to == 'X') {
                            cost = 2;
                        } else if (Character.isDigit(to)) {
                            int h2 = height[nx][ny];
                            int diff = Math.abs(h1 - h2);
                            if (diff == 0) cost = 1;
                            else if (diff == 1) cost = 3;
                            else continue; // too steep
                        }
                    }
    
                    if (time + cost < dist[nx][ny]) {
                        dist[nx][ny] = time + cost;
                        pq.add(new State(nx, ny, dist[nx][ny]));
                    }
                }
            }
    
            // Collect delivery times for each target
            List<Integer> deliveryTimes = new ArrayList<>();
            for (int[] t : targets) {
                int tx = t[0];
                int ty = t[1];
                if (dist[tx][ty] == Integer.MAX_VALUE) return -1; // unreachable
                deliveryTimes.add(dist[tx][ty]);
            }
    
            // Sort deliveries by delivery time (greedy helps with balancing)
            Collections.sort(deliveryTimes, Collections.reverseOrder());
    
            // Two delivery men - assign tasks in order of time using earliest available strategy
            int[] deliveryMan = new int[2]; // time when each delivery man is next available
    
            for (int time : deliveryTimes) {
                // Assign to the delivery man who is free the earliest
                if (deliveryMan[0] <= deliveryMan[1]) {
                    deliveryMan[0] += 2 * time;
                } else {
                    deliveryMan[1] += 2 * time;
                }
            }
    
            // The last delivery is the maximum of the two
            return Math.max(deliveryMan[0], deliveryMan[1]);
        }
}
    
    
