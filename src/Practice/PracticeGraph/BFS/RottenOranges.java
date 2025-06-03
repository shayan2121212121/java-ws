package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {

    /*
     * Given an int[][] grid containing below values:
     * 0 = empty cell
     * 1 = fresh orange
     * 2 = rotten orange
     * every minute oranges next to rotten orange gets rotten
     * find minimum mins it will take for all oranges to rot
     * if impossible for all oranges to rot return -1
     */
    public static int minutesToRot(int[][] grid){
        //create a direction matrix that defines possible movement increaments 
        //e.g for right move i remains same and j increses by 1
        int[][] directions = new int[][]{
            {0,1},//right
            {0,-1},//left
            {-1,0}, //up
            {1,0}//down

        };
        int minutes = 0;

        //create queue and visited
        Queue<int[]> queue = new ArrayDeque<>();

        int fresh = 0; // to store no of initial fresh oranges
        //populate queue with rotten orange nodes and count fresh
        for(int i = 0; i<grid.length;i++){
            for(int j=0;j<grid[0].length; j++){
                if(grid[i][j]==1){//fresh orange
                    fresh++;
                }
                if(grid[i][j]==2){//rotten orange
                    queue.add(new int[]{i,j});
                }
            }
        }

        //while loop
        while(!queue.isEmpty()){
            //get size for the for loop
            int queueSize = queue.size();
            boolean rottenRound = false;

            for(int i =0; i< queueSize; i++){
                //in for loop get curr node
                int[] currNode = queue.remove();
                //write a loop on directions matrix for all possible neighbor nodes
                for(int[] dir: directions){
                    int newX = currNode[0]+dir[0]; //current node i + dir i
                    int newY = currNode[1]+dir[1];
                    //check if newX and nwY are inn bound
                    if(newX>=0 && newX<=grid.length-1 && newY>=0 && newY <= grid[0].length-1 &&grid[newX][newY]==1 ){
                            grid[newX][newY]=2; //rot it
                            fresh--; //reduce fresh
                            queue.add(new int[]{newX,newY});//add to queue
                            rottenRound = true; //this level was countable
                        
                    }
                    
                }
            }
            if(rottenRound){
                minutes++;
            }
            
        }
        if(fresh==0){//all oranges went rotten
            return minutes;
        }else {//not possible to make all oranges rot
            return -1;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2, 2, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        int result = minutesToRot(grid);
        System.out.println("Minutes until all oranges are rotten: " + result);  // Output: 4
    }

}
