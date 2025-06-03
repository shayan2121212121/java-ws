package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIsland {

    //Given a int[][] grid of 1s and 0s where 0s is water find no of islands
    public static int noOfIslands(int[][] grid){
        //we need to do bfs for each 1s in grid till we hit water for all nodes neighbor then increase count. 
        //Maintain a visited coordinate set so that we don't perform bfs on same island

        //set to maintain visited
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;

        //loop to find 1s not in set to call 
        for (int i =0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j]==1 &&!visited[i][j]){
                    visited[i][j]=true;
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void bfs(int[][] grid, boolean[][] visited, int i, int j){
        Queue<int[]> queue = new ArrayDeque<>();
        //directions for allowed movement
        int[][] directions = new int[][]{
            {1,0},//down
            {-1,0}, //up
            {0,1},//right
            {0,-1}//left
        };
        //we need no level count as we are finding one island for current coordinate
        queue.add(new int[]{i,j});
        while(!queue.isEmpty()){
                //get current node
                int[] currNode = queue.remove();

                //no target so no check
                
                for (int[] dir: directions){
                    int nx = currNode[0]+dir[0];
                    int ny = currNode[1]+dir[1];

                    if(nx>=0 && nx<grid.length && ny>=0 && ny< grid[0].length){
                        //check if new node is 1 and not in visited set
                        if(grid[nx][ny]==1 && !visited[nx][ny]){
                            //add in queue and set
                            queue.add(new int[]{nx,ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            
        }
    }
    public static void main(String[] args) {
        int[][] grid = {
            {1,1,0,0,0},
            {1,1,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,1}
        };

        int result = noOfIslands(grid);
        System.out.println("Number of islands: " + result); // Output: 3
    }

}
