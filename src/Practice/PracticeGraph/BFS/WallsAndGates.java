package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class WallsAndGates {
    static int inf = Integer.MAX_VALUE;

    /*
     * given a int[][]grid with inf as empty room, 0 as gate and -1 as wall
     * Fill empty rooms with steps from that room to nearest gate 
    */
    public static int[][] stepsToGate(int[][] grid){
        //multiple start points - nodes with value 0
        //need level count as steps 

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j]==0){
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        //directions of move
        int[][] directions = new int[][]{
            {0,1},//right
            {0,-1},//left
            {1,0},//down
            {-1,0}//up
        };
        //bfs
        while(!queue.isEmpty()){
                //curr node
                int[] currNode = queue.remove();

                //movements
                for(int[] dir: directions){
                    int nx = currNode[0]+dir[0];
                    int ny = currNode[1]+dir[1];
                    //allowed movements
                    if(nx>=0 && nx<grid.length && ny>=0 && ny<grid[0].length){
                        
                        if(grid[nx][ny]==inf && !visited[nx][ny]){
                            grid[nx][ny]=grid[currNode[0]][currNode[1]]+1;
                            queue.add(new int[]{nx,ny});
                            visited[nx][ny]=true;
                        }
                    }
                }
        }

        return grid;
    }

    public static void main(String[] args) {
        int[][] rooms = {
            {inf,inf,0,inf},
            {inf,inf,inf,-1},
            {inf,-1,inf,-1},
            {0,-1,inf,inf}
        };

        stepsToGate(rooms);
        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }


}
