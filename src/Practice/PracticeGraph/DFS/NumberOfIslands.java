package Practice.PracticeGraph.DFS;

public class NumberOfIslands {
    //similar to max area but instead of area we need to no of islands in a given grid of 0s and 1s

    public static int noOfIslands(char[][] grid){
        //loop through the grid and call dfs when 1 is encountered
        //in dfs we will converts all 1s to 0 to update the grid so that we dount double count
        //increase counter everytime dfs is called no return in dfs
        int count =0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    public static void dfs(char[][] grid, int i, int j){
        //Step1 since no base case go for boundary cond
        if(i<0 || i>= grid.length || j<0 || j>=grid[0].length){
            return;
        }

        //Step2 invalid cell meaning 0 value
        if(grid[i][j]=='0'){
            return;
        }

        //Step3 mark visited - no temp needed as we don't want to revisit the character
        grid[i][j]='0';

        //Step4 directions or neighbors
        int[][] directions = new int[][]{
            {1,0},//up
            {-1,0},//down
            {0,1},//right
            {0,-1}//left
        };
        for(int[] dir : directions){
            int ni = i+dir[0];
            int nj = j + dir[1];
            //recursive dfs call
            dfs(grid, ni, nj);
        }
    }

    public static void main(String[] args) {

        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };

        System.out.println("Number of Islands: " + noOfIslands(grid)); // Output: 3
    }



}
