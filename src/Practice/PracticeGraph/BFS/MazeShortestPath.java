package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MazeShortestPath {

    //praactice bfs
    public static int bfsPractice(int[][] maze, int[] start, int[] end){
        int[][] directions = new int[][]{
            {0,1},//right
            {0,-1},//left
            {1,0},//down
            {-1,0}//up
        };

        //queue
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        Set<int[]> visited = new HashSet<>();
        visited.add(start);

        int moves = 0;

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                //curr node
                int[] currNode = queue.remove();

                //destination check
                if(currNode[0]==end[0] && currNode[1]==end[1])return moves;

                //moves
                for(int[] dir:directions){
                    int ni = currNode[0]+dir[0];
                    int nj = currNode[1]+dir[1];
                    //move cond
                    if(ni>=0 && ni<maze.length && nj>=0 && nj<maze[0].length && maze[ni][nj]==0){
                        if(!visited.contains(new int[]{ni,nj})){
                            queue.add(new int[]{ni,nj});
                            visited.add(new int[]{ni,nj});
                        }
                    }
                }
            }
            moves++;

        }
        return -1;
    }

    public static int minNumOfSteps(int[][] maze, int[] start, int[] end){
        /*
         * 0 represents an open path
         * 1 represents a wall
         * can move in 4 directions
         * return minimum no of steps needed to from start to reach end
         * return -1 if not possible
         */

         //each node will have i,j values of current char create queue and set
         Queue<int[]> queue = new ArrayDeque<>();
         Set<int[]> visited = new HashSet<>();


         //add root
         queue.add(start);
         visited.add(start);
         int steps = 0;

         while(!queue.isEmpty()){
            //steps needed find queue size to use in loop
            int queueSize = queue.size();

            for(int i = 0; i<queueSize; i++){
                //get current node
                int[] currNode = queue.remove();

                //check if target
                if(currNode[0]==end[0] && currNode[1]==end[1]){
                    return steps;
                }

                //find neighbors
                List<int[]> neighbors = getNeighbors(currNode[0],currNode[1], maze);
                //add to set and queue
                for(int[] neighbor : neighbors){
                    if(!visited.contains(neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
                
            }
            //increase step
            steps++;
         }

        return -1;
    }

    public static List<int[]> getNeighbors(int i, int j, int[][] maze){
        List<int[]> result = new ArrayList<>();
        int r = maze.length-1; //last row index
        int c = maze[0].length -1;//last col index

        //for any i,j node possible nodes are:
        //(i+1,j) given i<=second last row
        if(i<r){
            if(maze[i+1][j]!=1){//move possible
                int[] downMove = new int[]{i+1,j};
                result.add(downMove);
            }
            
        }
        //(i-1,j) given i is not the first row
        if(i>0){
            if(maze[i-1][j]!=1){
                int[] upMove = new int[]{i-1,j};
                result.add(upMove);
            }
            
        }
        //(i,j+1) given j is not the last column
        if(j<c){
            if(maze[i][j+1]!=1){
                int[] rightMove = new int[]{i,j+1};
                result.add(rightMove);
            }
            
        }
        //(i,j-1) given j is not the first column
        if(j>0){
            if(maze[i][j-1]!=1){
                int[] leftMove = new int[]{i,j-1};
                result.add(leftMove);
            }
            
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] maze = {
            {0, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}
        };
        int[] start = {0, 0};
        int[] end = {4, 3};

        int steps = bfsPractice(maze, start, end);
        System.out.println("Minimum steps: " + steps);  // Output: 7
    }

}
