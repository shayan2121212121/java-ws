package Practice.PracticeGraph.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumEffortPath {
    //minimize maximum height difference along a path

    static class Cell implements Comparable<Cell>{
        int row,col,effort;
        Cell(int row, int col, int effort){
            this.row = row;
            this.col = col;
            this.effort = effort;
        }

        @Override
        public int compareTo(Cell o) {
            return this.effort-o.effort;
        }

    }

    public static int minEffort(int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;
        //directions for movement
        int[][] directions = new int[][]{
            {0,1},//right
            {0,-1},//left
            {1,0},//up
            {-1,0}//down
        };

        //array store minimum effort to reach cell
        int[][] effortTo = new int[rows][cols];
        for(int[] row: effortTo){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        effortTo[0][0] = 0;//sourse
        //queue
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.add(new Cell(0, 0, 0));//root

        while(!pq.isEmpty()){
            //curr cell
            Cell currCell = pq.poll();
            int currRow = currCell.row;
            int currCol = currCell.col;
            int currEffort = currCell.effort;

            //check for target
            if(currRow==rows-1 && currCol==cols-1){
                return currEffort;
            }
            //direction loop basically getting neighbors
            for(int[] dir : directions){
                int newRow = currRow+dir[0];
                int newCol = currCol+dir[1];
                //check fopr bound
                if(newRow>=0 && newRow<rows && newCol>=0 && newCol<cols){
                    int nextEffort = Math.max(Math.abs(heights[currRow][currCol]-heights[newRow][newCol]), currEffort);
                    if(nextEffort<effortTo[newRow][newCol]){
                        effortTo[newRow][newCol]=nextEffort;
                        pq.add(new Cell(newRow, newCol, nextEffort));
                    }
                }
            }
        }
        return -1; //should not reach here
    }

    public static void main(String[] args) {
        int[][] heights = {
            {1, 2, 2},
            {3, 8, 2},
            {5, 3, 5}
        };

        int result = minEffort(heights);
        System.out.println("Minimum effort required: " + result); // Output: 2
    }

}
